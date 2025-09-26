package com.backoven.catdogshelter.domain.donation.command.domain.repository;

import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostCommentReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationPostCommentReportRepository extends JpaRepository<DonationPostCommentReport, Long> {}