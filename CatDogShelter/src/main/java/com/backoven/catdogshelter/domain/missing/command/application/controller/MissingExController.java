package com.backoven.catdogshelter.domain.missing.command.application.controller;

import com.backoven.catdogshelter.common.entity.ShelterHeadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.common.util.ReportCategory;
import com.backoven.catdogshelter.domain.missing.command.application.dto.CreateMissingCommentRequest;
import com.backoven.catdogshelter.domain.missing.command.application.dto.CreateMissingPostRequest;
import com.backoven.catdogshelter.domain.missing.command.application.dto.UpdateMissingPostRequest;
import com.backoven.catdogshelter.domain.missing.command.application.service.*;
import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPost;
import com.backoven.catdogshelter.domain.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missing-posts")
public class MissingExController {
    private final MissingPostCommandService missingPostService;
    private final MissingPostCommentCommandService missingCommentService;
    private final MissingPostLikeCommandService missingLikeService;
    private final MissingPostReportCommentCommandService missingPostCommentReportService;
    private final MissingPostFileService missingPostFileService;
    private final MissingPostReportCommandService missingPostReportService;
    private final UserRepository userRepository;
    /* =============== JPA - CUD =============== */

    // 게시글 생성
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Integer> createMissingPost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam(value = "headId", required = false) Integer headId,
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam("status") boolean status,
            @RequestParam(value = "sigunguId", required = false) Integer sigunguId,
            @RequestParam(value = "detailAddress", required = false) String detailAddress,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "registrationNum", required = false) Integer registrationNum,
            @RequestParam(value = "breed", required = false) String breed,
            @RequestParam(value = "animalType", required = false) String animalType,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "weight", required = false) Double weight,
            @RequestParam(value = "lostDate", required = false) String lostDate,
            @RequestParam(value = "feature", required = false) String feature,
            @RequestParam(value = "contact", required = false) String contact,
            @RequestPart(value = "files", required = false) List<MultipartFile> files) {

        if (headId == null && userId == null) {
            throw new IllegalArgumentException("회원 ID(userId) 또는 보호소장 ID(headId) 중 하나는 필수입니다.");
        }

        CreateMissingPostRequest request = new CreateMissingPostRequest();
        request.setTitle(title);
        request.setContent(content);
        request.setHeadId(headId);
        request.setUserId(userId);
        request.setStatus(status);
        request.setSigunguId(sigunguId);
        request.setDetailAddress(detailAddress);
        request.setGender(gender);
        request.setAge(age);
        request.setRegistrationNum(registrationNum);
        request.setBreed(breed);
        request.setAnimalType(animalType);
        request.setColor(color);
        request.setWeight(weight != null ? weight : 0.0);
        request.setLostDate(lostDate);
        request.setFeature(feature);
        request.setContact(contact);

        Integer postId = missingPostService.createMissingPost(request, files);
        return ResponseEntity.ok(postId);
    }

    // 게시글 수정
    @PutMapping(value = "/{postId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateMissingPost(
            @PathVariable Integer postId,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam(value = "headId", required = false) Integer headId,
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam("sigunguId") Integer sigunguId,
            @RequestParam("status") boolean status,
            @RequestParam(value = "detailAddress", required = false) String detailAddress,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "registrationNum", required = false) Integer registrationNum,
            @RequestParam(value = "breed", required = false) String breed,
            @RequestParam(value = "animalType", required = false) String animalType,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "weight", required = false) Double weight,
            @RequestParam(value = "lostDate", required = false) String lostDate,
            @RequestParam(value = "feature", required = false) String feature,
            @RequestParam(value = "contact", required = false) String contact,
            @RequestPart(value = "files", required = false) List<MultipartFile> files) {

        if (headId == null && userId == null) {
            throw new IllegalArgumentException("회원 ID(userId) 또는 보호소장 ID(headId) 중 하나는 필수입니다.");
        }

        UpdateMissingPostRequest dto = new UpdateMissingPostRequest();
        dto.setPostId(postId);
        dto.setTitle(title);
        dto.setContent(content);
        dto.setHeadId(headId);
        dto.setUserId(userId);
        dto.setSigunguId(sigunguId);
        dto.setStatus(status);
        dto.setDetailAddress(detailAddress);
        dto.setGender(gender);
        dto.setAge(age);
        dto.setRegistrationNum(registrationNum);
        dto.setBreed(breed);
        dto.setAnimalType(animalType);
        dto.setColor(color);
        dto.setWeight(weight != null ? weight : 0.0);
        dto.setLostDate(lostDate);
        dto.setFeature(feature);
        dto.setContact(contact);

        missingPostService.updateMissingPost(dto, files);
        return ResponseEntity.ok().build();
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMissingPost(@PathVariable Integer id,
                                                  @RequestParam(value = "headId", required = false) Integer headId,
                                                  @RequestParam(value = "userId", required = false) Integer userId) {
        missingPostService.deleteMissingPost(id, headId, userId);
        return ResponseEntity.noContent().build();
    }



    // 이미지 다운로드
    @GetMapping("/image/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
        byte[] imageData = missingPostFileService.downloadImage(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageData);
    }

    // 좋아요 누르기
    @PostMapping("/{id}/like")
    public ResponseEntity<Void> updateLikeMissingPost(@PathVariable Integer id, @RequestParam Integer userId) {
        missingLikeService.updateLikeMissingPost(id, userId);
        return ResponseEntity.ok().build();
    }

    // 좋아요 취소
    @DeleteMapping("/{id}/like")
    public ResponseEntity<Void> updateUnLikeMissingPost(@PathVariable Integer id, @RequestParam Integer userId) {
        missingLikeService.updateUnLikeMissingPost(id, userId);
        return ResponseEntity.noContent().build();
    }

    // 댓글 작성
    @PostMapping("/{id}/comments")
    public ResponseEntity<Integer> createMissingPostComment(@PathVariable Integer id,
                                                            @RequestBody CreateMissingCommentRequest request) {
        request.setPostId(id);
        return ResponseEntity.ok(missingCommentService.createMissingPostComment(request));
    }

    // 댓글 수정
    @PutMapping("/comments/{id}")
    public ResponseEntity<Void> updateMissingPostComment(@PathVariable Integer id,
                                                         @RequestParam Integer userId,
                                                         @RequestBody String content) {
        missingCommentService.updateMissingPostComment(id, userId, content);
        return ResponseEntity.ok().build();
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteMissingPostComment(@PathVariable Integer id,
                                                         @RequestParam Integer userId) {
        missingCommentService.deleteMissingPostComment(id, userId);
        return ResponseEntity.noContent().build();
    }

    // 일반 사용자 게시글 신고
    @PostMapping("/{postId}/report/user")
    public ResponseEntity<Void> createReportMissingPostByUser(@PathVariable Integer postId,
                                                 @RequestParam ReportCategory category,
                                                 @RequestParam(required = false) String detail,
                                                 @RequestParam Integer userId) {
        UserEntity user = new UserEntity();
        user.setUserId(userId);

        missingPostReportService.createReportMissingPostByUser(postId, category, detail, user);
        return ResponseEntity.ok().build();
    }

    // 보호소장 게시글 신고
    @PostMapping("/{postId}/report/head")
    public ResponseEntity<Void> createReportMissingPostByHead(@PathVariable Integer postId,
                                                 @RequestParam ReportCategory category,
                                                 @RequestParam(required = false) String detail,
                                                 @RequestParam Integer headId) {
        ShelterHeadEntity head = new ShelterHeadEntity();
        head.setId(headId);

        missingPostReportService.createReportMissingPostByHead(postId, category, detail, head);
        return ResponseEntity.ok().build();
    }



    // 일반 사용자 댓글 신고
    @PostMapping("/comments/{commentId}/report/user")
    public ResponseEntity<Void> createReportMissingPostCommentByUser(@PathVariable Integer commentId,
                                                    @RequestParam ReportCategory category,
                                                    @RequestParam(required = false) String detail,
                                                    @RequestParam Integer userId) {
        UserEntity user = new UserEntity();
        user.setUserId(userId); //

        missingPostCommentReportService.createReportMissingPostCommentByUser(commentId, category, detail, user);
        return ResponseEntity.ok().build();
    }

    // 보호소장 댓글 신고
    @PostMapping("/comments/{commentId}/report/head")
    public ResponseEntity<Void> createReportMissingPostCommentByHead(@PathVariable Integer commentId,
                                                    @RequestParam ReportCategory category,
                                                    @RequestParam(required = false) String detail,
                                                    @RequestParam Integer headId) {
        ShelterHeadEntity head = new ShelterHeadEntity();
        head.setId(headId); //

        missingPostCommentReportService.createReportMissingPostCommentByHead(commentId, category, detail, head);
        return ResponseEntity.ok().build();
    }

}

