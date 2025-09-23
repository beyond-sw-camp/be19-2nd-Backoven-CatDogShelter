package com.backoven.catdogshelter.domain.sighting.query.service;

import com.backoven.catdogshelter.domain.sighting.query.dto.SightingDetailDTO;
import com.backoven.catdogshelter.domain.sighting.query.dto.SightingSearchDTO;
import com.backoven.catdogshelter.domain.sighting.query.dto.SightingSummaryDTO;

import java.util.List;

public interface SightingService {
    List<SightingSummaryDTO> findSightingSummary(SightingSearchDTO search);

//    SightingDetailDTO findSightingDetails(int postId);
}
