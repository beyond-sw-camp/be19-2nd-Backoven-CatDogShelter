package com.backoven.catdogshelter.domain.sighting.command.domain.service;

import com.backoven.catdogshelter.domain.sighting.command.application.dto.RequestSightingPostCommentDTO;
import com.backoven.catdogshelter.domain.sighting.command.application.dto.RequestSightingPostDTO;

public interface DSightingService {
    void validatePost(RequestSightingPostDTO newPostDTO);

    void validatePost(RequestSightingPostCommentDTO newPostCommentDTO);
}
