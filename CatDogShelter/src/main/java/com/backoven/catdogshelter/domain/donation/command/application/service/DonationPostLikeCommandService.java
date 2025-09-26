package com.backoven.catdogshelter.domain.donation.command.application.service;

import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPost;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostLiked;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostLikedRepository;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostRepository;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DonationPostLikeCommandService {
    private final DonationPostRepository donationPostRepository;
    private final DonationPostLikedRepository donationPostLikedRepository;

    public void likePost(Long postId, UserEntity user) {
        DonationPost post = donationPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        if (donationPostLikedRepository.existsByPostAndUser(post, user)) {
            throw new IllegalArgumentException("Already liked");
        }

        DonationPostLiked like = new DonationPostLiked();
        like.setPost(post);
        like.setUser(user);

        donationPostLikedRepository.save(like);
    }
}
