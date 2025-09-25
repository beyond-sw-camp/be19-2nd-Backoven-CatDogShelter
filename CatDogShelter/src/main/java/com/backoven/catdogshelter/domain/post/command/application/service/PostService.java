package com.backoven.catdogshelter.domain.post.command.application.service;

import com.backoven.catdogshelter.domain.post.command.application.dto.PostCommentModifyDTO;
import com.backoven.catdogshelter.domain.post.command.application.dto.PostCommentRegistDTO;
import com.backoven.catdogshelter.domain.post.command.application.dto.PostModifyDTO;
import com.backoven.catdogshelter.domain.post.command.application.dto.PostRegistDTO;
import com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity.PostCommentEntity;
import com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity.PostEntity;
import com.backoven.catdogshelter.domain.post.command.domain.repository.PostCommentRepository;
import com.backoven.catdogshelter.domain.post.command.domain.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("postCommandService")
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final PostCommentRepository postCommentRepository;

    @Autowired
    public PostService(PostRepository postRepository, ModelMapper modelMapper, PostCommentRepository postCommentRepository) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.postCommentRepository = postCommentRepository;
    }

    /* 자유게시글 삽입(insert) 부분 */
    @Transactional
    public void registPost(PostRegistDTO postregist) {
        postRepository.save(modelMapper.map(postregist, PostEntity.class));
    }

    /* 자유게시글 수정(update) 부분 */
    @Transactional
    public void modifyPost(PostModifyDTO postModify) {
        PostEntity foundPost = postRepository.findById(postModify.getId())
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        // 수정할 값 덮어쓰기
        foundPost.setTitle(postModify.getTitle());
        foundPost.setContent(postModify.getContent());
        foundPost.setUpdatedAt(postModify.getUpdatedAt());

        /* 둘 중 하나의 값이 먼저 들어오면 다른 값은 null로 처리되도록 함 */
        /* if 문을 안 썼을 경우, 둘의 값을 같이 넣어야 하고 하나만 넣었을 경우 둘 다 null 값으로 처리됨. */
        if (postModify.getUserId() != null) {
            foundPost.setUserId(postModify.getUserId());
            foundPost.setHeadId(null);
        } else if (postModify.getHeadId() != null) {
            foundPost.setHeadId(postModify.getHeadId());
            foundPost.setUserId(null);
        }
    }

    /* 자유게시글 내용 논리적 삭제(delete) 부분 */
    @Transactional
    public void deletePost(int id){
        PostEntity foundPost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
        foundPost.setIsDeleted(1);
    }

    /* 자유게시글 댓글 삽입(insert) 부분 */
    @Transactional
    public void registPostComment(PostCommentRegistDTO postCommentRegist){
        postCommentRepository.save(modelMapper.map(postCommentRegist, PostCommentEntity.class));
    }

    /* 자유게시글 댓글 수정(update) 부분 */
    @Transactional
    public void modifyPostComment(PostCommentModifyDTO postCommentModify){
        PostCommentEntity foundComment = postCommentRepository.findById(postCommentModify.getId())
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        foundComment.setContent(postCommentModify.getContent());
        foundComment.setUpdatedAt(postCommentModify.getUpdatedAt());

        if (postCommentModify.getUserId() != null) {
            foundComment.setUserId(postCommentModify.getUserId());
            foundComment.setHeadId(null);
        } else if (postCommentModify.getHeadId() != null) {
            foundComment.setHeadId(postCommentModify.getHeadId());
            foundComment.setUserId(null);
        }
    }

    @Transactional
    public void deletePostComment(int id){
        PostCommentEntity foundComment = postCommentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
        foundComment.setIsDeleted(1);
    }
}
