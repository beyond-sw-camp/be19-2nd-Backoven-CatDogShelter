package com.backoven.catdogshelter.domain.adoption.command.application.controller;

import com.backoven.catdogshelter.domain.adoption.command.application.dto.*;
import com.backoven.catdogshelter.domain.adoption.command.application.service.AdoptionPostCommandService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("catdogshelter/adoption-post")
@Tag(name="입양 게시글 CUD API")
public class AdoptionPostCommandController {

    private final AdoptionPostCommandService adoptionPostCommandService;

    @Autowired
    public AdoptionPostCommandController(
            AdoptionPostCommandService adoptionPostCommandService) {
        this.adoptionPostCommandService = adoptionPostCommandService;
    }

    // 게시글 좋아요 토글 (회원 or 보호소장)
    @Operation(summary = "게시글 추천",
            description = "토글과 같이 추천과 추천취소가 실행")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdoptionPostLikedDTO.class)))})
    @PostMapping("/{postId}/liked")
    public ResponseEntity<?> updateAdoptionPostLiked(
            @PathVariable int postId,
            @RequestBody AdoptionPostLikedDTO dto) {
        boolean resultLiked = adoptionPostCommandService.updateAdoptionPostLiked(postId, dto.getUserId(), dto.getHeadId());
        if(resultLiked) return ResponseEntity.ok().body("해당 게시글을 추천하였습니다.");
        return ResponseEntity.ok().body("해당 게시글 추천을 취소하였습니다.");
    }
    // 게시글 등록 + 파일 업로드
    @Operation(summary = "게시글 등록",
            description = "파일과 함께 게시글 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "생성 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdoptionPostCommandDTO.class)))})
    @PostMapping("/regist")
    public ResponseEntity<?> registAdoptionPost(
            @ModelAttribute AdoptionPostCommandDTO newPost
    ) throws IOException {
        adoptionPostCommandService.registAdoptionPost(newPost);
        return ResponseEntity.created(URI.create("/adoption/post/board")).build();
    }
    // 게시글 수정 + 파일 재업로드
    @Operation(summary = "게시글 수정",
            description = "파일과 재업로드 포함 게시글 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdoptionPostCommandDTO.class)))})
    @PutMapping("/{postId}")
    public ResponseEntity<?> modifyAdoptionPost(
            @PathVariable int postId,
            @ModelAttribute AdoptionPostCommandDTO modifyPost) throws IOException {

        adoptionPostCommandService.modifyAdoptionPost(postId, modifyPost);

        return ResponseEntity.ok().body("게시글이 수정되었습니다.");
    }
    // 게시글 파일 조회
    @Operation(summary = "게시글 파일 조회", description = "게시글에 첨부된 파일 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "파일 조회 성공",
                    content = @Content(mediaType = "application/octet-stream")),
            @ApiResponse(responseCode = "404", description = "파일 없음")})
    @GetMapping("/{postId}/files/{fileName}")
    public ResponseEntity<Resource> serveFile(
            @PathVariable int postId,
            @PathVariable String fileName) throws IOException {

        // 실제 저장 경로 (postId는 여기선 사용 안 하지만 RESTful 경로를 위해 포함)
        Path file = Paths.get("/Users/haeone/Desktop/be19-2nd-backoven-petShelter/uploads/")
                .resolve(fileName);

        Resource resource = new UrlResource(file.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        // 확장자 기반 Content-Type 설정
        String contentType = Files.probeContentType(file);
        if (contentType == null) {
            contentType = "application/octet-stream"; // 알 수 없는 경우
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
    // 게시글 삭제 -> soft delete, 상태 변경
    @Operation(summary = "게시글 삭제", description = "게시글을 soft delete 처리")
    @ApiResponse(responseCode = "200", description = "삭제 성공")
    @PutMapping("/{postId}/blind")
    public ResponseEntity<?> removeAdoptionPost(@PathVariable int postId){
        adoptionPostCommandService.deleteAdoptionPost(postId);
        return ResponseEntity.ok().body("게시글이 삭제되었습니다.");
    }
    // 게시글에 댓글 등록
    @Operation(summary = "댓글 등록", description = "게시글에 댓글 작성")
    @ApiResponse(responseCode = "200", description = "댓글 등록 성공")
    @PostMapping("/{postId}/comment")
    public ResponseEntity<?> registAdoptionPostComment(
            @PathVariable int postId,
            @RequestBody AdoptionPostCommentDTO newComment) {

        adoptionPostCommandService.registAdoptionPostComment(postId, newComment);

        return ResponseEntity.ok().body("댓글이 등록되었습니다.");
//                .created(URI.create("adoption/post/"+postId)).build();
    }
    // 게시글에 댓글 수정
    @Operation(summary = "댓글 수정", description = "게시글의 댓글 수정")
    @ApiResponse(responseCode = "200", description = "댓글 수정 성공")
    @PutMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<?> modifyAdoptionPostComment(
            @PathVariable int postId,
            @PathVariable int commentId,
            @RequestBody AdoptionPostCommentDTO adoptionPostComment){

        adoptionPostCommandService.modifyAdoptionPostComment(postId,commentId,adoptionPostComment);

        return ResponseEntity.ok().body("댓글이 수정되었습니다.");
//        AdoptionPostCommentDTO responseDto = modelMapper.map(foundComment, AdoptionPostCommentDTO.class);
//        return ResponseEntity.ok(responseDto);
    }
    // 게시글에 댓글 삭제 -> soft delete, 상태 변경
    @Operation(summary = "댓글 삭제", description = "댓글 soft delete 처리")
    @ApiResponse(responseCode = "200", description = "댓글 삭제 성공")
    @PutMapping("{postId}/comment/{commentId}/blind")
    public ResponseEntity<?> deleteAdoptionPostComment(
            @PathVariable int postId,
            @PathVariable int commentId){

        adoptionPostCommandService.deleteAdoptionPostComment(postId,commentId);
        return ResponseEntity.ok().body("댓글이 삭제되었습니다.");
    }
    // 게시글 신고
    @Operation(summary = "게시글 신고", description = "게시글 신고 접수")
    @ApiResponse(responseCode = "200", description = "신고 성공")
    @PostMapping("/{postId}/report")
    public ResponseEntity<?> registAdoptionPostReport(
            @PathVariable int postId,
            @RequestBody AdoptionPostReportDTO adoptionPostReportDto){
        adoptionPostCommandService.registAdoptionPostReport(postId, adoptionPostReportDto);
        return ResponseEntity.ok().body("게시글이 신고되었습니다.");
    }
    // 댓글 신고
    @Operation(summary = "댓글 신고", description = "댓글 신고 접수")
    @ApiResponse(responseCode = "200", description = "신고 성공")
    @PostMapping("/{postId}/report/comment/{commentId}")
    public ResponseEntity<?> registAdoptionPostCommentReport(
            @PathVariable int postId,
            @PathVariable int commentId,
            @RequestBody AdoptionPostCommentReportDTO adoptionPostReportDto) {
        adoptionPostCommandService.registAdoptionPostCommentReport(postId, commentId, adoptionPostReportDto);
        return ResponseEntity.ok().body("댓글이 신고되었습니다.");
    }
}
