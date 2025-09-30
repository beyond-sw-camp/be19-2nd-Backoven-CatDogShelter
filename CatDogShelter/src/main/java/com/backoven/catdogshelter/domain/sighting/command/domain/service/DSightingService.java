package com.backoven.catdogshelter.domain.sighting.command.domain.service;

import com.backoven.catdogshelter.domain.sighting.command.application.dto.*;

public interface DSightingService {
    void validate(RequestSightingPostDTO newPostDTO);

    void validate(RequestSightingPostCommentDTO newPostCommentDTO);

    void validate(RequestSightingPostReportDTO newReportDTO);

    void validate(RequestSightingPostCommentReportDTO newReportDTO);

    void validate(RequestSightingPostLikedDTO newLikedDTO);
}
