package com.backoven.catdogshelter.domain.sighting.query.mapper;

import com.backoven.catdogshelter.domain.sighting.query.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SightingMapper {

    List<SightingSummaryDTO> selectSightingSummary(SightingSearchDTO search);

    SightingDetailDTO selectSightingDetails(int postId);    // 상세 보기

    void incrementSightingView(int postId);

    List<SightingPostReportDTO> selectSightingPostReport(int postId);

    List<SightingPostCommentReportDTO> selectSightingPostCommentReport(int commentId);
}
