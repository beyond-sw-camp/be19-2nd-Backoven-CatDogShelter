package com.backoven.catdogshelter.domain.donation.command.application.service;

import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPost;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostLiked;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostLikedRepository;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DonationPostLikeCommandService {
    private final DonationPostRepository donationPostRepository;
    private final DonationPostLikedRepository donationPostLikedRepository;

    // 좋아요 누르기
    public void updateLikeDonationPost(Integer postId, Integer userId) {
        DonationPost post = donationPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        // 임시 UserEntity (DB조회 생략)
        UserEntity user = new UserEntity();
        user.setUserId(userId);

        if (donationPostLikedRepository.existsByPostAndUser(post, user)) {
            throw new IllegalStateException("이미 좋아요를 눌렀습니다.");
        }

        DonationPostLiked like = new DonationPostLiked();
        like.setPost(post);
        like.setUser(user);

        donationPostLikedRepository.save(like);
    }

    // 좋아요 취소
    public void updateUnLikeDonationPost(Integer postId, Integer userId) {
        DonationPost post = donationPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        UserEntity user = new UserEntity();
        user.setUserId(userId);

        if (!donationPostLikedRepository.existsByPostAndUser(post, user)) {
            throw new IllegalStateException("좋아요 기록이 없습니다.");
        }

        donationPostLikedRepository.deleteByPostAndUser(post, user);
    }
}
