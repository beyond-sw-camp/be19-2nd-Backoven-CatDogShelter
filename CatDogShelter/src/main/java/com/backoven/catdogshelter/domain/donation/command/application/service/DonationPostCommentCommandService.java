package com.backoven.catdogshelter.domain.donation.command.application.service;

import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.domain.donation.command.application.dto.CreateDonationCommentRequest;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPost;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostComment;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostCommentRepository;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostRepository;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DonationPostCommentCommandService {

    private final DonationPostRepository donationPostRepository;
    private final DonationPostCommentRepository donationPostCommentRepository;


    //특정 게시글에 댓글작성
    public Long createDonationPostComment(CreateDonationCommentRequest dto) {
        DonationPost post = donationPostRepository.findById(dto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        UserEntity user = new UserEntity();
        user.setId(dto.getUserId()); // 더미 UserEntity (추후 UserService 연동)

        DonationPostComment comment = new DonationPostComment();
        comment.setContent(dto.getContent());
        comment.setCreatedAt(DateTimeUtil.now());
        comment.setPost(post);
        comment.setUser(user);

        return donationPostCommentRepository.save(comment).getId();
    }

    //댓글 수정(작성자 본인만)
    @Transactional
    public void updateDonationPostComment(Long id, Long userId, String content) {
        DonationPostComment comment = donationPostCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        // 작성자 본인 검증
        if (comment.getUser() == null || !comment.getUser().getId().equals(userId)) {
            throw new IllegalStateException("댓글 작성자만 수정할 수 있습니다.");
        }

        comment.setContent(content);
        comment.setUpdatedAt(DateTimeUtil.now());
    }


    //댓글 삭제(작성자 본인만)
    public void deleteDonationPostComment(Long id, Long userId) {
        DonationPostComment comment = donationPostCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        // 작성자 본인인지 확인
        if (comment.getUser() == null || !comment.getUser().getId().equals(userId)) {
            throw new IllegalStateException("댓글 작성자만 삭제할 수 있습니다.");
        }

        comment.softDelete();
    }
}
