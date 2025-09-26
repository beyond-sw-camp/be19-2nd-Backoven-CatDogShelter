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
        // 목격 정보 게시판의 필드 중에 nullable하게 만들 것들 확인 필요
        
        return ResponseEntity
                .created(URI.create("/sighting-post/" + postId))   // Response Header 중 "Location"에 담겨 돌아옴
                .build();
    }
    // 게시글 수정
    @PutMapping("/post/{postId}")
    public ResponseEntity<?> modifySightingPost(@PathVariable int postId,@RequestBody RequestSightingPostDTO modifyPost) {
        aSightingService.modifySightingPost(postId, modifyPost);

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
