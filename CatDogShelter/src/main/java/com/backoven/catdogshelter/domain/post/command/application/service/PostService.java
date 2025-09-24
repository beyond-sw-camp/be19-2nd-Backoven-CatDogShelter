package com.backoven.catdogshelter.domain.post.command.application.service;

import com.backoven.catdogshelter.domain.post.command.application.dto.PostModifyDTO;
import com.backoven.catdogshelter.domain.post.command.application.dto.PostRegistDTO;
import com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity.PostEntity;
import com.backoven.catdogshelter.domain.post.command.domain.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("postCommandService")
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PostService(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    /* 자유게시글 삽입(insert) 부분 */
    @Transactional
    public void registPost(PostRegistDTO postregist) {
        postRepository.save(modelMapper.map(postregist, PostEntity.class));
    }

    /* 자유게시글 수정(update) 부분 */
    @Transactional
    public void modifyPost(PostModifyDTO modifyPost) {
        PostEntity foundPost = postRepository.findById(modifyPost.getId())
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        // 수정할 값 덮어쓰기
        foundPost.setTitle(modifyPost.getTitle());
        foundPost.setContent(modifyPost.getContent());
        foundPost.setUpdatedAt(modifyPost.getUpdatedAt());

        /* 둘 중 하나의 값이 먼저 들어오면 다른 값은 null로 처리되도록 함 */
        /* if 문을 안 썼을 경우, 둘의 값을 같이 넣어야 하고 하나만 넣었을 경우 둘 다 null 값으로 처리됨. */
        if (modifyPost.getUserId() != null) {
            foundPost.setUserId(modifyPost.getUserId());
            foundPost.setHeadId(null);
        } else if (modifyPost.getHeadId() != null) {
            foundPost.setHeadId(modifyPost.getHeadId());
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
}
