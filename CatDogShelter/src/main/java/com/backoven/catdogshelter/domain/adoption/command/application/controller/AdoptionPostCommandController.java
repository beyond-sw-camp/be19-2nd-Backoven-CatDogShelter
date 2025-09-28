package com.backoven.catdogshelter.domain.adoption.command.application.controller;

import com.backoven.catdogshelter.domain.adoption.command.application.dto.*;
import com.backoven.catdogshelter.domain.adoption.command.application.service.AdoptionPostCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RestController
@RequestMapping("/catdogshelter/adoption-post")
public class AdoptionPostCommandController {

    private final AdoptionPostCommandService adoptionPostCommandService;

    @Autowired
    public AdoptionPostCommandController(AdoptionPostCommandService adoptionPostCommandService) {
        this.adoptionPostCommandService = adoptionPostCommandService;
    }

    // 게시글 좋아요 토글 (회원 or 보호소장)
    @PostMapping("/{postId}/liked")
    public ResponseEntity<?> updateAdoptionPostLiked(
            @PathVariable int postId,
            @RequestBody AdoptionPostLikedDTO dto) {
        boolean resultLiked = adoptionPostCommandService.updateAdoptionPostLiked(postId, dto.getUserId(), dto.getHeadId());
        if (resultLiked) return ResponseEntity.ok().body("해당 게시글을 추천하였습니다.");
        return ResponseEntity.ok().body("해당 게시글 추천을 취소하였습니다.");
    }

    // 게시글 등록 + 파일 업로드
    @PostMapping("/regist")
    public ResponseEntity<?> registAdoptionPost(
            @ModelAttribute AdoptionPostCommandDTO newPost) throws IOException {
        adoptionPostCommandService.registAdoptionPost(newPost);
        return ResponseEntity.created(URI.create("/adoption/post/board")).build();
    }

    // 게시글 수정 + 파일 재업로드
    @PutMapping("/{postId}")
    public ResponseEntity<?> modifyAdoptionPost(
            @PathVariable int postId,
            @ModelAttribute AdoptionPostCommandDTO modifyPost) throws IOException {
        adoptionPostCommandService.modifyAdoptionPost(postId, modifyPost);
        return ResponseEntity.ok().body("게시글이 수정되었습니다.");
    }

    // 게시글 파일 조회
    @GetMapping("/{postId}/files/{fileName}")
    public ResponseEntity<Resource> serveFile(
            @PathVariable int postId,
            @PathVariable String fileName) throws IOException {

        Path file = Paths.get("/Users/haeone/Desktop/be19-2nd-backoven-petShelter/uploads/")
                .resolve(fileName);

        Resource resource = new UrlResource(file.toUri());
        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        String contentType = Files.probeContentType(file);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    // 게시글 삭제 -> soft delete
    @PutMapping("/{postId}/blind")
    public ResponseEntity<?> removeAdoptionPost(@PathVariable int postId) {
        adoptionPostCommandService.deleteAdoptionPost(postId);
        return ResponseEntity.ok().body("게시글이 삭제되었습니다.");
    }

    // 댓글 등록
    @PostMapping("/{postId}/comment")
    public ResponseEntity<?> registAdoptionPostComment(
            @PathVariable int postId,
            @RequestBody AdoptionPostCommentDTO newComment) {
        adoptionPostCommandService.registAdoptionPostComment(postId, newComment);
        return ResponseEntity.ok().body("댓글이 등록되었습니다.");
    }

    // 댓글 수정
    @PutMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<?> modifyAdoptionPostComment(
            @PathVariable int postId,
            @PathVariable int commentId,
            @RequestBody AdoptionPostCommentDTO adoptionPostComment) {
        adoptionPostCommandService.modifyAdoptionPostComment(postId, commentId, adoptionPostComment);
        return ResponseEntity.ok().body("댓글이 수정되었습니다.");
    }

    // 댓글 삭제 -> soft delete
    @PutMapping("/{postId}/comment/{commentId}/blind")
    public ResponseEntity<?> deleteAdoptionPostComment(
            @PathVariable int postId,
            @PathVariable int commentId) {
        adoptionPostCommandService.deleteAdoptionPostComment(postId, commentId);
        return ResponseEntity.ok().body("댓글이 삭제되었습니다.");
    }

    // 게시글 신고
    @PostMapping("/{postId}/report")
    public ResponseEntity<?> registAdoptionPostReport(
            @PathVariable int postId,
            @RequestBody AdoptionPostReportDTO adoptionPostReportDto) {
        adoptionPostCommandService.registAdoptionPostReport(postId, adoptionPostReportDto);
        return ResponseEntity.ok().body("게시글이 신고되었습니다.");
    }

    // 댓글 신고
    @PostMapping("/{postId}/report/comment/{commentId}")
    public ResponseEntity<?> registAdoptionPostCommentReport(
            @PathVariable int postId,
            @PathVariable int commentId,
            @RequestBody AdoptionPostCommentReportDTO adoptionPostReportDto) {
        adoptionPostCommandService.registAdoptionPostCommentReport(postId, commentId, adoptionPostReportDto);
        return ResponseEntity.ok().body("댓글이 신고되었습니다.");
    }
}
