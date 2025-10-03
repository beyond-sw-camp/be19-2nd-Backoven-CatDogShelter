package com.backoven.catdogshelter.domain.missing.command.application.service;

import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.common.enumulation.ReportCategory;
import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPost;
import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPostReport;
import com.backoven.catdogshelter.domain.missing.command.domain.repository.MissingPostReportRepository;
import com.backoven.catdogshelter.domain.missing.command.domain.repository.MissingPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissingPostReportCommandService {
    private final MissingPostRepository missingPostRepository;
    private final MissingPostReportRepository missingPostReportRepository;

    public void createReportMissingPostByUser(Integer postId, ReportCategory category, String detail, UserEntity user) {
        MissingPost post = missingPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        if (missingPostReportRepository.existsByPostAndUser(post, user)) {
            throw new IllegalStateException("이미 신고한 게시글입니다.");
        }

        MissingPostReport report = new MissingPostReport();
        report.setPost(post);
        report.setCategory(category);
        report.setEtcDetail(detail);
        report.setCreatedAt(DateTimeUtil.now());
        report.setUser(user);

        missingPostReportRepository.save(report);
    }

    public void createReportMissingPostByHead(Integer postId, ReportCategory category, String detail, ShelterheadEntity head) {
        MissingPost post = missingPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        if (missingPostReportRepository.existsByPostAndHead(post, head)) {
            throw new IllegalStateException("이미 신고한 게시글입니다.");
        }

        MissingPostReport report = new MissingPostReport();
        report.setPost(post);
        report.setCategory(category);
        report.setEtcDetail(detail);
        report.setCreatedAt(DateTimeUtil.now());
        report.setHead(head);

        missingPostReportRepository.save(report);
    }
}
