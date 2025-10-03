package com.backoven.catdogshelter.domain.donation.command.application.service;

import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.common.enumulation.ReportCategory;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostComment;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostCommentReport;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostCommentReportRepository;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DonationPostReportCommentCommandService {

    private final DonationPostCommentRepository donationPostCommentRepository;
    private final DonationPostCommentReportRepository donationPostCommentReportRepository;

    public void createReportDonationPostCommentByUser(Integer commentId, ReportCategory category, String detail, UserEntity user) {
        DonationPostComment comment = donationPostCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        if (donationPostCommentReportRepository.existsByCommentAndUser(comment, user)) {
            throw new IllegalStateException("이미 신고한 댓글입니다.");
        }

        DonationPostCommentReport report = new DonationPostCommentReport();
        report.setComment(comment);
        report.setCategory(category);
        report.setEtcDetail(detail);
        report.setCreatedAt(DateTimeUtil.now());
        report.setUser(user);

        donationPostCommentReportRepository.save(report);
    }

    public void createReportDonationPostCommentByHead(Integer commentId, ReportCategory category, String detail, ShelterheadEntity head) {
        DonationPostComment comment = donationPostCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        if (donationPostCommentReportRepository.existsByCommentAndHead(comment, head)) {
            throw new IllegalStateException("이미 신고한 댓글입니다.");
        }

        DonationPostCommentReport report = new DonationPostCommentReport();
        report.setComment(comment);
        report.setCategory(category);
        report.setEtcDetail(detail);
        report.setCreatedAt(DateTimeUtil.now());
        report.setHead(head);

        donationPostCommentReportRepository.save(report);
    }
}
