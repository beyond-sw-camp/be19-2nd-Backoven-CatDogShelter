package com.backoven.catdogshelter.domain.post.command.application.service;

import com.backoven.catdogshelter.domain.post.command.application.dto.*;
import com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity.PostCommentEntity;
import com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity.PostEntity;
import com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity.PostFilesEntity;
import com.backoven.catdogshelter.domain.post.command.domain.repository.PostCommentRepository;
import com.backoven.catdogshelter.domain.post.command.domain.repository.PostFilesRepository;
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
    private final PostFilesRepository postFilesRepository;

    @Autowired
    public PostService(PostRepository postRepository, ModelMapper modelMapper, PostCommentRepository postCommentRepository, PostFilesRepository postFilesRepository) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.postCommentRepository = postCommentRepository;
        this.postFilesRepository = postFilesRepository;
    }

    /* 자유게시글 삽입(insert) 부분 */
    @Transactional
    public void registPost(PostRegistDTO postregist) {
        PostEntity entity = modelMapper.map(postregist, PostEntity.class);
        entity.setCreatedAtNow();
        postRepository.save(entity);
    }

    /* 자유게시글 수정(update) 부분 */
    @Transactional
    public void modifyPost(PostModifyDTO postModify) {
        PostEntity foundPost = postRepository.findById(postModify.getId())
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        // 수정할 값 덮어쓰기
        foundPost.setTitle(postModify.getTitle());
        foundPost.setContent(postModify.getContent());
        foundPost.setUpdatedAtNow();

        /* 둘 중 하나의 값이 먼저 들어오면 다른 값은 null로 처리되도록 함 */
        /* if 문을 안 썼을 경우, 둘의 값을 같이 넣어야 하고 하나만 넣었을 경우 둘 다 null 값으로 처리됨. */
//        if (postModify.getUserId() != null) {
//            foundPost.setUserId(postModify.getUserId());
//            foundPost.setHeadId(null);
//        } else if (postModify.getHeadId() != null) {
//            foundPost.setHeadId(postModify.getHeadId());
//            foundPost.setUserId(null);
//        }
    }

    /* 자유게시글 내용 논리적 삭제(delete) 부분 */
    @Transactional
    public void deletePost(int postDelete){
        PostEntity foundPost = postRepository.findById(postDelete)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
        foundPost.setIsDeleted(1);
    }

    /* 자유게시글 댓글 삽입(insert) 부분 */
    @Transactional
    public void registPostComment(PostCommentRegistDTO postCommentRegist){
        PostCommentEntity entity = modelMapper.map(postCommentRegist, PostCommentEntity.class);
        entity.setCreatedAtNow();
        postCommentRepository.save(entity);
    }

    /* 자유게시글 댓글 수정(update) 부분 */
    @Transactional
    public void modifyPostComment(PostCommentModifyDTO postCommentModify){
        PostCommentEntity foundComment = postCommentRepository.findById(postCommentModify.getId())
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        foundComment.setContent(postCommentModify.getContent());
        foundComment.setUpdatedAtNow();

//        if (postCommentModify.getUserId() != null) {
//            foundComment.setUserId(postCommentModify.getUserId());
//            foundComment.setHeadId(null);
//        } else if (postCommentModify.getHeadId() != null) {
//            foundComment.setHeadId(postCommentModify.getHeadId());
//            foundComment.setUserId(null);
//        }
    }

    /* 자유게시글 댓글 삭제(delete) 부분 */
    @Transactional
    public void deletePostComment(int postCommentDelete){
        PostCommentEntity foundComment = postCommentRepository.findById(postCommentDelete)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
        foundComment.setIsDeleted(1);
    }

    /* 자유게시글 파일 삽입(insert) 부분 */
    @Transactional
    public void registPostFiles(PostFilesRegistDTO postFilesRegist){
        PostFilesEntity entity = modelMapper.map(postFilesRegist, PostFilesEntity.class);
        entity.setUploadedAtNow();
        postFilesRepository.save(entity);
    }

    /* 자유게시글 파일 수정(modify) 부분 */
    @Transactional
    public void modifyPostFiles(PostFilesModifyDTO postFilesModify){
        PostFilesEntity foundFiles = postFilesRepository.findById(postFilesModify.getId())
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        foundFiles.setFileRename(postFilesModify.getFileRename());
        foundFiles.setUploadedAtNow();
    }

    /* 자유게시글 파일 삭제(delete) 부분 */
    /* 자유게시글과 댓글은 is_deleted를 이용하여 논리적 삭제만 이루어졌지만, 파일의 경우 바로 삭제되므로 그에 맞게 코드 작성 */
    @Transactional
    public void deletePostFiles(PostFilesDeleteDTO postFilesDelete){
        int id = postFilesDelete.getId();
        postFilesRepository.deleteById(id);
    }
}
