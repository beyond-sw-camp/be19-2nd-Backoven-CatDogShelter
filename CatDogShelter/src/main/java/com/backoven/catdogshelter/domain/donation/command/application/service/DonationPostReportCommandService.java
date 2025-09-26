package com.backoven.catdogshelter.domain.donation.command.application.service;

import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.common.util.ReportCategory;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostComment;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostCommentReport;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostCommentReportRepository;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostCommentRepository;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DonationPostReportCommandService {

    private final DonationPostCommentRepository donationPostCommentRepository;
    private final DonationPostCommentReportRepository donationPostCommentReportRepository;

    public void reportComment(Long commentId, ReportCategory category, String detail, UserEntity reporter) {
        DonationPostComment comment = donationPostCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        DonationPostCommentReport report = new DonationPostCommentReport();
        report.setComment(comment);
        report.setCategory(category);
        report.setEtcDetail(detail);
        report.setCreatedAt(DateTimeUtil.now());
        report.setUser(reporter);

        donationPostCommentReportRepository.save(report);
    }
}
