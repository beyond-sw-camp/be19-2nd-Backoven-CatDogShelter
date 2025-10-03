package com.backoven.catdogshelter.domain.donation.command.domain.repository;

import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostComment;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostCommentReport;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DonationPostCommentReportRepository extends JpaRepository<DonationPostCommentReport, Long> {
    boolean existsByCommentAndUser(DonationPostComment comment, UserEntity user);
    boolean existsByCommentAndHead(DonationPostComment comment, ShelterheadEntity head);
}