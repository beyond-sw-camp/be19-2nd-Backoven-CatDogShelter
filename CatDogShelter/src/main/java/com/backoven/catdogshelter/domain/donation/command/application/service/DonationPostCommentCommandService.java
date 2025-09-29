package com.backoven.catdogshelter.domain.donation.command.application.service;

import com.backoven.catdogshelter.common.entity.UserEntity;
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


    //특정 게시글에 댓글작성
    public Integer createDonationPostComment(CreateDonationCommentRequest dto) {
        DonationPost post = donationPostRepository.findById(dto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        UserEntity user = new UserEntity();
        user.setUserId(dto.getUserId()); // 더미 UserEntity (추후 UserService 연동)

        DonationPostComment comment = new DonationPostComment();
        comment.setContent(dto.getContent());
        comment.setCreatedAt(DateTimeUtil.now());
        comment.setPost(post);
        comment.setUser(user);

        return donationPostCommentRepository.save(comment).getId();
    }

    //댓글 수정(작성자 본인만)
    @Transactional
    public void updateDonationPostComment(Integer id, Integer userId, String content) {
        DonationPostComment comment = donationPostCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        // 작성자 본인 검증
        if (comment.getUser() == null || !comment.getUser().getUserId().equals(userId)) {
            throw new IllegalStateException("댓글 작성자만 수정할 수 있습니다.");
        }

        comment.setContent(content);
        comment.setUpdatedAt(DateTimeUtil.now());
    }


    @Transactional
    //댓글 삭제(작성자 본인만)
    public void deleteDonationPostComment(Integer id, Integer userId) {
        DonationPostComment comment = donationPostCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        if (comment.getUser() == null || !comment.getUser().getUserId().equals(userId)) {
            throw new IllegalStateException("댓글 작성자만 삭제할 수 있습니다.");
        }

        comment.softDelete(); // 영속 상태에서 필드 변경
        // 별도의 save() 필요 없음 (flush 시점에 자동 반영)
    }
}
