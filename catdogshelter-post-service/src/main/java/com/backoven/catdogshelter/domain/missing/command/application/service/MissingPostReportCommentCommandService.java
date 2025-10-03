package com.backoven.catdogshelter.domain.missing.command.application.service;

import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.common.enumulation.ReportCategory;
import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPostComment;
import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPostCommentReport;
import com.backoven.catdogshelter.domain.missing.command.domain.repository.MissingPostCommentReportRepository;
import com.backoven.catdogshelter.domain.missing.command.domain.repository.MissingPostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissingPostReportCommentCommandService {
    private final MissingPostCommentRepository missingPostCommentRepository;
    private final MissingPostCommentReportRepository missingPostCommentReportRepository;

    // 일반 사용자 신고
    public void createReportMissingPostCommentByUser(Integer commentId, ReportCategory category, String detail, UserEntity user) {
        MissingPostComment comment = missingPostCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        if (missingPostCommentReportRepository.existsByCommentAndUser(comment, user)) {
            throw new IllegalStateException("이미 이 댓글을 신고했습니다.");
        }

        MissingPostCommentReport report = new MissingPostCommentReport();
        report.setComment(comment);
        report.setCategory(category);
        report.setEtcDetail(detail);
        report.setCreatedAt(DateTimeUtil.now());
        report.setUser(user);

        missingPostCommentReportRepository.save(report);
    }

    // 보호소장 신고
    public void createReportMissingPostCommentByHead(Integer commentId, ReportCategory category, String detail, ShelterheadEntity head) {
        MissingPostComment comment = missingPostCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        if (missingPostCommentReportRepository.existsByCommentAndHead(comment, head)) {
            throw new IllegalStateException("이미 이 댓글을 신고했습니다.");
        }

        MissingPostCommentReport report = new MissingPostCommentReport();
        report.setComment(comment);
        report.setCategory(category);
        report.setEtcDetail(detail);
        report.setCreatedAt(DateTimeUtil.now());
        report.setHead(head);

        missingPostCommentReportRepository.save(report);
    }
}
