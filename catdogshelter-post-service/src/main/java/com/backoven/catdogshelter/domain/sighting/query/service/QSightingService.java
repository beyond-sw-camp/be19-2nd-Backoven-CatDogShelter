package com.backoven.catdogshelter.domain.sighting.query.service;

import com.backoven.catdogshelter.domain.sighting.query.dto.*;

import java.util.List;

public interface QSightingService {
    List<SightingSummaryDTO> findSightingSummary(SightingSearchDTO search);

    SightingDetailDTO findSightingDetails(int postId);

    List<SightingPostReportDTO> findSightingPostReport(int postId);

    List<SightingPostCommentReportDTO> findSightingPostCommentReport(int commentId);
}
