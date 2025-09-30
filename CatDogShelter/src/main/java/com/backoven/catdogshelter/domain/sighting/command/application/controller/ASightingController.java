package com.backoven.catdogshelter.domain.sighting.command.application.controller;

import com.backoven.catdogshelter.domain.sighting.command.application.dto.*;
import com.backoven.catdogshelter.domain.sighting.command.application.service.ASightingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sighting-post")
@Tag(name = "목격 정보 게시글 CUD API")
public class ASightingController {

    private final ASightingService aSightingService;

    @Autowired
    public ASightingController(ASightingService ASightingService) {
        this.aSightingService = ASightingService;
    }

    // 게시글 작성
    @PostMapping("/post")
    @Operation(summary = "게시글 등록", description = "들어온 정보를 통해 게시글을 생성해 등록(파일 등록 가능)")
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
    @Operation(summary = "게시글 수정", description = "입력값을 id로 하는 게시글의 내용을 수정합니다. \n파일이 들어오면 기존 파일을 전체 삭제 후 새로 들어온 파일을 등록합니다.")
    public ResponseEntity<?> modifySightingPost(@PathVariable int postId,
                                                @RequestParam("modifyPostDTO") String modifyPostDTOJson,    // RequestParam과 RequestBody 동시 사용 불가
                                                @RequestParam List<MultipartFile> multiFiles) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        RequestSightingPostDTO modifyPostDTO = objectMapper.readValue(modifyPostDTOJson, RequestSightingPostDTO.class);

        aSightingService.modifySightingPost(postId, modifyPostDTO, multiFiles);

        return ResponseEntity
                .created(URI.create("/sighting-post/" + postId))   // Response Header 중 "Location"에 담겨 돌아옴
                .build();
    }

    // 게시글 삭제
    @DeleteMapping("/post/{postId}")
    @Operation(summary = "게시글 삭제", description = "입력값을 id로 하는 게시글을 삭제합니다. \n연결된 파일들은 모두 삭제합니다.\n게시글: soft delete\n파일: hard delete")
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
    @Operation(summary = "게시글 삭제 복원", description = "soft delete된 게시글의 삭제 상태값을 변경합니다. \n파일은 hard delete로 복원되지 않습니다.")
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
    @Operation(summary = "게시글 블라인드", description = "입력값을 id로 하는 게시글을 가려서 조회되지 않도록 합니다. \nsoft delete와 동일한 구조로 동작합니다.")
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
    @Operation(summary = "댓글 작성", description = "입력을 토대로 특정 게시글에 댓글을 작성합니다.")
    public ResponseEntity<?> registSightingPostComment(@RequestBody RequestSightingPostCommentDTO newCommentDTO) {
        aSightingService.registSightingPostComment(newCommentDTO);

        return ResponseEntity
                .created(URI.create("/sighting-post/" + newCommentDTO.getPostId()))
                .build();
    }

    // 댓글 수정
    @PutMapping("/comment/{commentId}")
    @Operation(summary = "댓글 수정", description = "입력값을 id로 가지는 댓글을 수정합니다.")
    public ResponseEntity<?> modifySightingPostComment(@PathVariable int commentId, @RequestBody RequestSightingPostCommentDTO modifyCommentDTO) {
        aSightingService.modifySightingPostComment(commentId, modifyCommentDTO);

        return ResponseEntity
                .created(URI.create("/sighting-post/" + modifyCommentDTO.getPostId()))   // Response Header 중 "Location"에 담겨 돌아옴
                .build();
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{commentId}")
    @Operation(summary = "댓글 삭제", description = "입력값을 id로 하는 댓글을 삭제합니다.(soft delete)")
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
    @Operation(summary = "댓글 복원", description = "soft delete된 댓글의 삭제 상태값을 변경합니다.")
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
    @Operation(summary = "댓글 블라인드", description = "입력값을 id로 하는 댓글을 가려서 조회되지 않도록 합니다. \nsoft delete와 동일한 구조로 동작합니다.")
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
    @Operation(summary = "게시글 신고", description = "입력값을 토대로 게시글에 대한 신고를 등록합니다.")
    public ResponseEntity<?> registSightingPostReport(@RequestBody RequestSightingPostReportDTO newReportDTO) {
        aSightingService.registSightingPostReport(newReportDTO);

        return ResponseEntity.noContent().build();
    }
    // 댓글 신고
    @PostMapping("/comment-report")
    @Operation(summary = "댓글 신고", description = "입력값을 토대로 댓글에 대한 신고를 등록합니다.")
    public ResponseEntity<?> registSightingPostCommentReport(@RequestBody RequestSightingPostCommentReportDTO newReportDTO) {
        aSightingService.registSightingPostCommentReport(newReportDTO);

        return ResponseEntity.noContent().build();
    }
    // 게시글 추천
    @PostMapping("/post-like")
    @Operation(summary = "게시글 추천", description = "누른 사람의 번호와 게시글 번호를 통해 추천을 기록합니다.")
    public ResponseEntity<?> registSightingPostLiked(@RequestBody RequestSightingPostLikedDTO newLikedDTO) {
        aSightingService.registSightingPostLiked(newLikedDTO);

        return ResponseEntity.noContent().build();
    }

    // 이미지 삭제
    @DeleteMapping("/{postId}/files")
    @Operation(summary = "이미지 삭제", description = "입력값을 id로 하는 게시글의 파일들을 삭제합니다.(hard delete)")
    public ResponseEntity<?> deleteSightinPostFile(@PathVariable int postId) {
        aSightingService.deleteSightinPostFile(postId);

        return ResponseEntity.noContent().build();
    }

    // 신고 처리
    @PatchMapping("/post-report/{postId}/{approve}")
    @Operation(summary = "게시글 신고 처리", description = "첫 번째 입력을 통해서 몇번 게시글에 대한 신고 처리인지 나타냅니다. \n두 번째 입력을 통해서 신고들을 통과 시킬것인지 말 것인지 나타냅니다.\n기본 5개이상 쌓였을때만 처리하기 때문에 한번 해당 메소드가 한번 통과되면 바로 게시글이 블라인드 된다.")
    public ResponseEntity<?> modifySightingPostReport(@PathVariable int postId,
                                                      @PathVariable boolean approve) {
        aSightingService.modifySightingPostReport(postId, approve);

        return ResponseEntity.noContent().build();
    }

    // 신고 처리
    @PatchMapping("/comment-report/{commentId}/{approve}")
    @Operation(summary = "댓글 신고 처리", description = "첫 번째 입력을 통해서 몇번 댓글에 대한 신고 처리인지 나타냅니다. \n두 번째 입력을 통해서 신고들을 통과 시킬것인지 말 것인지 나타냅니다.\n기본 5개이상 쌓였을때만 처리하기 때문에 한번 해당 메소드가 한번 통과되면 바로 댓글이 블라인드 된다.")
    public ResponseEntity<?> modifySightingPostCommentReport(@PathVariable int commentId,
                                                             @PathVariable boolean approve) {
        aSightingService.modifySightingPostCommentReport(commentId, approve);

        return ResponseEntity.noContent().build();
    }

}
