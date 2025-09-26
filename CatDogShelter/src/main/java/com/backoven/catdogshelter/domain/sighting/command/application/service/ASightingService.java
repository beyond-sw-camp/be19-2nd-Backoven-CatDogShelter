package com.backoven.catdogshelter.domain.sighting.command.application.service;

import com.backoven.catdogshelter.domain.sighting.command.application.dto.RequestSightingPostDTO;

public interface ASightingService {
    int registSightingPost(RequestSightingPostDTO newPost);

    void modifySightingPost(int postId, RequestSightingPostDTO modifyPost);

    boolean removeSightingPost(int postId);

    boolean blindSightingPost(int postId);

    boolean restoreSightingPost(int postId);
}
