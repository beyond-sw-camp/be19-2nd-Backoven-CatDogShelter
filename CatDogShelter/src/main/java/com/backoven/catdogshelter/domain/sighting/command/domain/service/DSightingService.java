package com.backoven.catdogshelter.domain.sighting.command.domain.service;

import com.backoven.catdogshelter.domain.sighting.command.application.dto.RequestSightingPostCommentDTO;
import com.backoven.catdogshelter.domain.sighting.command.application.dto.RequestSightingPostCommentReportDTO;
import com.backoven.catdogshelter.domain.sighting.command.application.dto.RequestSightingPostDTO;
import com.backoven.catdogshelter.domain.sighting.command.application.dto.RequestSightingPostReportDTO;

public interface DSightingService {
    void validate(RequestSightingPostDTO newPostDTO);

    void validate(RequestSightingPostCommentDTO newPostCommentDTO);

    void validate(RequestSightingPostReportDTO newReportDTO);

    void validate(RequestSightingPostCommentReportDTO newReportDTO);
}
