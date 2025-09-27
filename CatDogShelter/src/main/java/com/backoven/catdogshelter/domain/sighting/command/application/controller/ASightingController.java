package com.backoven.catdogshelter.domain.sighting.command.application.controller;

import com.backoven.catdogshelter.domain.sighting.command.application.dto.*;
import com.backoven.catdogshelter.domain.sighting.command.application.service.ASightingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sighting-post")
public class ASightingController {

    private final ASightingService aSightingService;

    @Autowired
    public ASightingController(ASightingService ASightingService) {
        this.aSightingService = ASightingService;
    }

    // 게시글 작성
    @PostMapping("/post")
    public ResponseEntity<?> registSightingPost(@RequestParam("newPostDTO") String newPostDTOJson,    // RequestParam과 RequestBody 동시 사용 불가
                                                @RequestParam List<MultipartFile> multiFiles) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        RequestSightingPostDTO newPostDTO = objectMapper.readValue(newPostDTOJson, RequestSightingPostDTO.class);

        int postId = aSightingService.registSightingPost(newPostDTO, multiFiles);
        // 목격 정보 게시판의 필드 중에 nullable하게 만들 것들 확인 필요
        
        return ResponseEntity
                .created(URI.create("/sighting-post/" + postId))   // Response Header 중 "Location"에 담겨 돌아옴
                .build();
    }

    // 게시글 수정
    @PutMapping("/post/{postId}")
    public ResponseEntity<?> modifySightingPost(@PathVariable int postId,@RequestBody RequestSightingPostDTO modifyPostDTO) {
        aSightingService.modifySightingPost(postId, modifyPostDTO);

        return ResponseEntity
                .created(URI.create("/sighting-post/" + postId))   // Response Header 중 "Location"에 담겨 돌아옴
                .build();
    }

    // 게시글 삭제
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<?> removeSightingPost(@PathVariable int postId) {
        if (aSightingService.removeSightingPost(postId)) {
            // 게시글이 존재해서 변경
        } else {
            // 게시글 존재 X
        }
        return ResponseEntity.noContent().build();
    }

    // 삭제된 게시글 복원
    @PatchMapping("/post/{postId}/restore")
    public ResponseEntity<?> restoreSightingPost(@PathVariable int postId) {
        if (aSightingService.restoreSightingPost(postId)) {
            // 게시글이 존재해서 되살림
        } else {
            // 게시글이 존재 X
        }

        return ResponseEntity.noContent().build();
    }

    // 게시글 블라인드
    @PatchMapping("/post/{postId}/blind")
    public ResponseEntity<?> blindSightingPost(@PathVariable int postId) {
        if (aSightingService.blindSightingPost(postId)) {
            // 게시글이 존재해서 변경
        } else {
            // 게시글 존재 X
        }

        return ResponseEntity.noContent().build();
    }

    // 댓글 작성
    @PostMapping("/comment")
    public ResponseEntity<?> registSightingPostComment(@RequestBody RequestSightingPostCommentDTO newCommentDTO) {
        aSightingService.registSightingPostComment(newCommentDTO);

        return ResponseEntity
                .created(URI.create("/sighting-post/" + newCommentDTO.getPostId()))
                .build();
    }

    // 댓글 수정
    @PutMapping("/comment/{commentId}")
    public ResponseEntity<?> modifySightingPostComment(@PathVariable int commentId, @RequestBody RequestSightingPostCommentDTO modifyCommentDTO) {
        aSightingService.modifySightingPostComment(commentId, modifyCommentDTO);

        return ResponseEntity
                .created(URI.create("/sighting-post/" + modifyCommentDTO.getPostId()))   // Response Header 중 "Location"에 담겨 돌아옴
                .build();
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<?> removeSightingPostComment(@PathVariable int commentId) {
        if (aSightingService.removeSightingPostComment(commentId)) {
            // 게시글이 존재해서 변경
        } else {
            // 게시글 존재 X
        }
        return ResponseEntity.noContent().build();
    }

    // 댓글 복원
    @PatchMapping("/comment/{commentId}/restore")
    public ResponseEntity<?> restoreSightingPostComment(@PathVariable int commentId) {
        if (aSightingService.restoreSightingPostComment(commentId)) {
            // 게시글이 존재해서 변경
        } else {
            // 게시글 존재 X
        }
        return ResponseEntity.noContent().build();
    }

    // 댓글 블라인드
    @PatchMapping("/comment/{commentId}/blind")
    public ResponseEntity<?> blindSightingPostComment(@PathVariable int commentId) {
        if (aSightingService.blindSightingPostComment(commentId)) {
            // 게시글이 존재해서 변경
        } else {
            // 게시글 존재 X
        }

        return ResponseEntity.noContent().build();
    }

    // 게시글 신고
    @PostMapping("/post-report")
    public ResponseEntity<?> registSightingPostReport(@RequestBody RequestSightingPostReportDTO newReportDTO) {
        aSightingService.registSightingPostReport(newReportDTO);

        return ResponseEntity.noContent().build();
    }
    // 댓글 신고
    @PostMapping("/comment-report")
    public ResponseEntity<?> registSightingPostCommentReport(@RequestBody RequestSightingPostCommentReportDTO newReportDTO) {
        aSightingService.registSightingPostCommentReport(newReportDTO);

        return ResponseEntity.noContent().build();
    }
    // 게시글 추천
    @PostMapping("/post-like")
    public ResponseEntity<?> registSightingPostLiked(@RequestBody RequestSightingPostLikedDTO newLikedDTO) {
        aSightingService.registSightingPostLiked(newLikedDTO);

        return ResponseEntity.noContent().build();
    }
}
