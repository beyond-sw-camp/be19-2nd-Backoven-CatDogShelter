package com.backoven.catdogshelter.domain.sighting.command.application.controller;

import com.backoven.catdogshelter.domain.sighting.command.application.dto.RequestSightingPostDTO;
import com.backoven.catdogshelter.domain.sighting.command.application.service.ASightingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
    public ResponseEntity<?> registSightingPost(@RequestBody RequestSightingPostDTO newPost) {
        int postId = aSightingService.registSightingPost(newPost);

        return ResponseEntity
                .created(URI.create("/sighting-post/" + postId))   // Response Header 중 "Location"에 담겨 돌아옴
                .build();
    }
//    // 게시글 수정
//    @PutMapping("/post")
//    public ResponseEntity<?> modifySightingPost() {
//
//    }
//    // 게시글 삭제
//    @DeleteMapping("/post/{postId}")
//    public ResponseEntity<?> removeSightingPost() {
//
//    }
//    // 댓글 작성
//    @PostMapping("/comment")
//    public ResponseEntity<?> registSightingPostComment() {
//
//    }
//    // 댓글 수정
//    @PutMapping("/comment")
//    public ResponseEntity<?> modifySightingPostComment() {
//
//    }
//    // 댓글 삭제
//    @DeleteMapping("/comment/{commentId}")
//    public ResponseEntity<?> removeSightingPostComment() {
//
//    }
//    // 게시글 신고
//    @PostMapping("/post-report")
//    public ResponseEntity<?> registSightingPostReport() {
//
//    }
//    // 댓글 신고
//    @PostMapping("/comment-report")
//    public ResponseEntity<?> registSightingPostCommentReport() {
//
//    }
//    // 게시글 추천
//    @PostMapping("/post-like")
//    public ResponseEntity<?> registSightingPostLiked() {
//
//    }
}
