package com.backoven.catdogshelter.domain.sighting.query.controller;

import com.backoven.catdogshelter.domain.sighting.query.dto.*;
import com.backoven.catdogshelter.domain.sighting.query.service.QSightingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sighting-post")
@Slf4j
public class QSightingController {

    private final QSightingService qSightingService;

    @Autowired
    public QSightingController(QSightingService QSightingService) {
        this.qSightingService = QSightingService;
    }

    // 목록 조회
    @GetMapping("/summary")
    public List<SightingSummaryDTO> findSightingSummary(@ModelAttribute SightingSearchDTO search) {

//        List<SightingSummaryDTO> sightingSummaryDTO = sightingService.findSightingSummary();
//        return sightingSummaryDTO;
//        log.info("controller 계층: {}", search.toString());
        return qSightingService.findSightingSummary(search);
    }

    // 상세 조회
    @GetMapping("/{postId}")
    public SightingDetailDTO findSightingDetails(@PathVariable int postId) {

        SightingDetailDTO sightingDetailDTO = qSightingService.findSightingDetails(postId);

        return sightingDetailDTO;
    }

    // 게시글 신고 조회
    @GetMapping("/post-report/{postId}")
    public List<SightingPostReportDTO> findSightingPostReport(@PathVariable int postId) {
        return qSightingService.findSightingPostReport(postId);
    }

    // 댓글 신고 조회
    @GetMapping("/comment-report/{commentId}")
    public List<SightingPostCommentReportDTO> findSightingPostCommentReport(@PathVariable int commentId) {
        return qSightingService.findSightingPostCommentReport(commentId);
    }
}
