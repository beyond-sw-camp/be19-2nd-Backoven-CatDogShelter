package com.backoven.catdogshelter.domain.donation.command.domain.repository;

import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DonationPostCommentRepository extends JpaRepository<DonationPostComment, Integer> {
    // 삭제 안 된 댓글만 조회
    List<DonationPostComment> findAllByPostIdAndDeletedFalse(Integer postId);

    Optional<DonationPostComment> findByIdAndDeletedFalse(Integer id);


}

