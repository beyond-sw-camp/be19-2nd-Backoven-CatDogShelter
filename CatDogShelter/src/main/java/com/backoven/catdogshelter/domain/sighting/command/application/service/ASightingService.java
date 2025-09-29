package com.backoven.catdogshelter.domain.sighting.command.application.service;

import com.backoven.catdogshelter.domain.sighting.command.application.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ASightingService {
    int registSightingPost(RequestSightingPostDTO newPostDTO, List<MultipartFile> multiFiles);

    void modifySightingPost(int postId, RequestSightingPostDTO modifyPostDTO, List<MultipartFile> multiFiles);

    boolean removeSightingPost(int postId);

    boolean restoreSightingPost(int postId);

    boolean blindSightingPost(int postId);

    void registSightingPostComment(RequestSightingPostCommentDTO newCommentDTO);

    void modifySightingPostComment(int commentId, RequestSightingPostCommentDTO modifyCommentDTO);

    boolean removeSightingPostComment(int commentId);

    boolean restoreSightingPostComment(int commentId);

    boolean blindSightingPostComment(int commentId);

    void registSightingPostReport(RequestSightingPostReportDTO newReportDTO);

    void registSightingPostCommentReport(RequestSightingPostCommentReportDTO newReportDTO);

    void registSightingPostLiked(RequestSightingPostLikedDTO newLikedDTO);

    void deleteSightinPostFile(int postId);

    void modifySightingPostReport(int postId, boolean approve);

    void modifySightingPostCommentReport(int commentId, boolean approve);
}
