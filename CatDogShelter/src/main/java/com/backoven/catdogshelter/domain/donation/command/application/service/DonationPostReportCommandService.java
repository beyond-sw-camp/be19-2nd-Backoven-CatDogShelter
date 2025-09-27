package com.backoven.catdogshelter.domain.donation.command.application.service;

import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.common.util.ReportCategory;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPost;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostReport;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostReportRepository;
import com.backoven.catdogshelter.domain.donation.command.domain.repository.DonationPostRepository;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DonationPostReportCommandService {

    private final DonationPostRepository donationPostRepository;
    private final DonationPostReportRepository donationPostReportRepository;


    public void reportPost(Long postId, ReportCategory category, String detail, UserEntity reporter) {
        DonationPost post = donationPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        DonationPostReport report = new DonationPostReport();
        report.setPost(post);
        report.setCategory(category);
        report.setEtcDetail(detail);
        report.setCreatedAt(DateTimeUtil.now());
        report.setUser(reporter);

        donationPostReportRepository.save(report);
    }

}
