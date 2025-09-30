package com.backoven.catdogshelter.domain.missing.command.application.service;


import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPost;
import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPostLiked;
import com.backoven.catdogshelter.domain.missing.command.domain.repository.MissingPostLikedRepository;
import com.backoven.catdogshelter.domain.missing.command.domain.repository.MissingPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MissingPostLikeCommandService {
    private final MissingPostRepository missingPostRepository;
    private final MissingPostLikedRepository missingPostLikedRepository;

    // 좋아요 누르기
    public void updateLikeMissingPost(Integer postId, Integer userId) {
        MissingPost post = missingPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));


        UserEntity user = new UserEntity();
        user.setUserId(userId);

        if (missingPostLikedRepository.existsByPostAndUser(post, user)) {
            throw new IllegalStateException("이미 좋아요를 눌렀습니다.");
        }

        MissingPostLiked like = new MissingPostLiked();
        like.setPost(post);
        like.setUser(user);

        missingPostLikedRepository.save(like);
    }

    // 좋아요 취소
    public void updateUnLikeMissingPost(Integer postId, Integer userId) {
        MissingPost post = missingPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        UserEntity user = new UserEntity();
        user.setUserId(userId);

        if (!missingPostLikedRepository.existsByPostAndUser(post, user)) {
            throw new IllegalStateException("좋아요 기록이 없습니다.");
        }

        missingPostLikedRepository.deleteByPostAndUser(post, user);
    }
}
