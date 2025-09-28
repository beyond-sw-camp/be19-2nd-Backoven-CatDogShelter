package com.backoven.catdogshelter.domain.donation.command.domain.repository;

import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationPostCommentRepository extends JpaRepository<DonationPostComment, Integer> {}

