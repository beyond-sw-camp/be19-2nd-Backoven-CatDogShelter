package com.backoven.catdogshelter.domain.sighting.query.service;

import com.backoven.catdogshelter.domain.sighting.query.dto.*;
import com.backoven.catdogshelter.domain.sighting.query.mapper.SightingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QSightingServiceImpl implements QSightingService {

    private final SightingMapper sightingMapper;

    @Autowired
    public QSightingServiceImpl(SightingMapper sightingMapper) {
        this.sightingMapper = sightingMapper;
    }

    // 목록 가져오기
    @Override
    public List<SightingSummaryDTO> findSightingSummary(SightingSearchDTO search) {
//        List<SightingSummaryDTO> sightingSummaryDTO = sightingMapper.selectSightingSummary();
//        return sightingSummaryDTO;
        return sightingMapper.selectSightingSummary(search);
    }

    @Override
    @Transactional  // 상세 조회를 들어가면 조회수 +1
    public SightingDetailDTO findSightingDetails(int postId) {

        SightingDetailDTO sightingDetailDTO = sightingMapper.selectSightingDetails(postId);

        sightingMapper.incrementSightingView(postId);  // 조회수 업데이트

        return sightingDetailDTO;
    }

    @Override
    public List<SightingPostReportDTO> findSightingPostReport(int postId) {
        return sightingMapper.selectSightingPostReport(postId);
    }

    @Override
    public List<SightingPostCommentReportDTO> findSightingPostCommentReport(int commentId) {
        return sightingMapper.selectSightingPostCommentReport(commentId);
    }
}
