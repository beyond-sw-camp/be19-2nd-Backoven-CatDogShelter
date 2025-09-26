package com.backoven.catdogshelter.domain.sighting.command.application.service;

import com.backoven.catdogshelter.domain.sighting.command.application.dto.RequestSightingPostCommentDTO;
import com.backoven.catdogshelter.domain.sighting.command.application.dto.RequestSightingPostDTO;

public interface ASightingService {
    int registSightingPost(RequestSightingPostDTO newPostDTO);

    void modifySightingPost(int postId, RequestSightingPostDTO modifyPostDTO);

    boolean removeSightingPost(int postId);

    boolean restoreSightingPost(int postId);

    boolean blindSightingPost(int postId);

    void registSightingPostComment(RequestSightingPostCommentDTO newCommentDTO);

    void modifySightingPostComment(int commentId, RequestSightingPostCommentDTO modifyCommentDTO);

    boolean removeSightingPostComment(int commentId);

    boolean restoreSightingPostComment(int commentId);

    boolean blindSightingPostComment(int commentId);
}
