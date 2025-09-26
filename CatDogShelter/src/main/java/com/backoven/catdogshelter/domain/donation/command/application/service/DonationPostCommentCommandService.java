package com.backoven.catdogshelter.domain.donation.command.application.service;

import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.domain.donation.command.application.dto.CreateDonationCommentRequest;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPost;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostComment;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostCommentRepository;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DonationPostCommentCommandService {

    private final DonationPostRepository donationPostRepository;
    private final DonationPostCommentRepository donationPostCommentRepository;

    public Long createComment(CreateDonationCommentRequest dto) {
        DonationPost post = donationPostRepository.findById(dto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        DonationPostComment comment = new DonationPostComment();
        comment.setContent(dto.getContent());
        comment.setCreatedAt(DateTimeUtil.now());
        comment.setPost(post);

        return donationPostCommentRepository.save(comment).getId();
    }

    public void deleteComment(Long id) {
        DonationPostComment comment = donationPostCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        comment.softDelete();
    }
}
