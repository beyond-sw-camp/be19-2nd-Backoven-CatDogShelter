// package: com.backoven.catdogshelter.domain.volunteer.command.application.controller
package com.backoven.catdogshelter.domain.volunteer.command.application.controller;

import com.backoven.catdogshelter.domain.volunteer.command.application.dto.*;
import com.backoven.catdogshelter.domain.volunteer.command.application.service.VolunteerPostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/volunteer-posts")
public class VolunteerPostCommandController {

    private final VolunteerPostService volunteerPostService;
    private final ObjectMapper om;

    @Autowired
    public VolunteerPostCommandController(VolunteerPostService volunteerPostService, ObjectMapper om) {
        this.volunteerPostService = volunteerPostService;
        this.om = om;
    }

    // 생성: multipart (dto + files[])
    @PostMapping(value = "/write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> writeVolunteerPost(
            @RequestPart("dto") String dtoJson,
            @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) throws Exception {
        var dto = om.readValue(dtoJson, VolunteerPostCreateDTO.class);
        Integer id = volunteerPostService.writeVolunteerPost(dto, files == null ? List.of() : files);
        return ResponseEntity.ok(Map.of("postId", id));
    }

    // 수정: multipart (dto + newFiles[])
    @PatchMapping(value = "/{id}/modify", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> modifyVolunteerPost(
            @PathVariable Integer id,
            @RequestPart("dto") String dtoJson,
            @RequestPart(value = "files", required = false) List<MultipartFile> newFiles
    ) throws Exception {
        var dto = om.readValue(dtoJson, VolunteerPostUpdateDTO.class);
        volunteerPostService.modifyVolunteerPost(id, dto, newFiles == null ? List.of() : newFiles);
        return ResponseEntity.noContent().build();
    }

    // 소프트 삭제
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteVolunteerPost(@PathVariable Integer id) {
        volunteerPostService.deleteVolunteerPost(id);
        return ResponseEntity.noContent().build();
    }

    // 좋아요 토글
    @PostMapping("/{id}/like")
    public ResponseEntity<Map<String, Object>> toggleLike(
            @PathVariable Integer id,
            @RequestBody LikeToggleRequest req
    ) {
        boolean liked = volunteerPostService.toggleLike(id, req);
        return ResponseEntity.ok(Map.of("liked", liked));
    }

    // 게시글 신고
//    @PostMapping("/{id}/report")
//    public ResponseEntity<Void> reportPost(
//            @PathVariable Integer id,
//            @RequestBody PostReportRequest req
//    ) {
//        req.setPostId(id);
//        volunteerPostService.reportPost(req);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }

    // 봉사후기 게시글 신고 기능 추가
    @PostMapping("/{postId}/report")
    public ResponseEntity<?> reportVolunteerPost(
            @PathVariable Integer postId,
            @RequestBody VolunteerPostReportCreateRequest req
    ) {
        try {
            req.setPostId(postId); // path로 고정
            Integer id = volunteerPostService.reportVolunteerPost(req);
            return ResponseEntity.ok(Map.of("reportId", id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    // 댓글 작성
    @PostMapping("/{id}/comment")
    public ResponseEntity<Map<String, Object>> addVolunteerPostComment(
            @PathVariable Integer id,
            @RequestBody CommentCreateDTO dto
    ) {
        dto.setPostId(id);
        Integer cmtId = volunteerPostService.addVolunteerPostComment(dto);
        return ResponseEntity.ok(Map.of("commentId", cmtId));
    }

    // 댓글 수정
    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<Void> modifyVolunteerPostComment(
            @PathVariable Integer commentId,
            @RequestBody CommentUpdateDTO dto
    ) {
        volunteerPostService.modifyVolunteerPostComment(commentId, dto);
        return ResponseEntity.noContent().build();
    }

    // 댓글 삭제(soft)
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteVolunteerPostComment(@PathVariable Integer commentId) {
        volunteerPostService.deleteVolunteerPostComment(commentId);
        return ResponseEntity.noContent().build();
    }

    // 댓글 신고
//    @PostMapping("/comments/{commentId}/report")
//    public ResponseEntity<Void> reportComment(
//            @PathVariable Integer commentId,
//            @RequestBody CommentReportRequest req
//    ) {
//        req.setCommentId(commentId);
//        volunteerPostService.reportComment(req);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }

    // 댓글 신고 기능 추가
    @PostMapping("/comments/{commentId}/report")
    public ResponseEntity<?> reportVolunteerPostComment(
            @PathVariable Integer commentId,
            @RequestBody VolunteerCommentReportCreateRequest req
    ) {
        try {
            req.setCommentId(commentId);
            Integer id = volunteerPostService.reportVolunteerPostComment(req);
            return ResponseEntity.ok(Map.of("reportId", id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
