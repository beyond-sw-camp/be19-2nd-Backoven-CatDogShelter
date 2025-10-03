package com.backoven.catdogshelter.domain.missing.command.application.controller;

import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.common.enumulation.ReportCategory;
import com.backoven.catdogshelter.domain.missing.command.application.dto.CreateMissingCommentRequest;
import com.backoven.catdogshelter.domain.missing.command.application.dto.CreateMissingPostRequest;
import com.backoven.catdogshelter.domain.missing.command.application.dto.UpdateMissingPostRequest;
import com.backoven.catdogshelter.domain.missing.command.application.service.*;
import com.backoven.catdogshelter.common.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missing-posts")
@Tag(name = "실종신고 게시글 CUD API")
public class MissingExController {
    private final MissingPostCommandService missingPostService;
    private final MissingPostCommentCommandService missingCommentService;
    private final MissingPostLikeCommandService missingLikeService;
    private final MissingPostReportCommentCommandService missingPostCommentReportService;
    private final MissingPostFileService missingPostFileService;
    private final MissingPostReportCommandService missingPostReportService;
    private final UserRepository userRepository;
    /* =============== JPA - CUD =============== */

    @Operation(
            summary = "실종신고 게시글 등록",
            description = "새로운 실종신고 게시글을 등록합니다."
    )
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
    @Operation(
            summary = "실종신고 게시글 수정",
            description = "입력값을 id로 하는 게시글의 내용을 글 작성자만 수정합니다. 파일이 들어오면 기존 파일을 전체 삭제 후 새로 들어온 파일을 등록합니다."
    )
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

    @Operation(
            summary = "실종신고 게시글 삭제",
            description = "새입력값을 id로 하는 게시글을 글 작성자만 삭제합니다. 게시글: soft delete"
    )
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

    @Operation(
            summary = "실종신고 게시글 좋아요 증가",
            description = "실종신고 게시글 좋아요 증가처리, 게시글 하나에 한 회원이 중복 증가 불가능"
    )
    // 좋아요 누르기
    @PostMapping("/{id}/like")
    public ResponseEntity<Void> updateLikeMissingPost(@PathVariable Integer id, @RequestParam Integer userId) {
        missingLikeService.updateLikeMissingPost(id, userId);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "실종신고 게시글 좋아요 감소",
            description = "실종신고 게시글 좋아요 감소처리, 본인이 좋아요 누른 게시글에만 좋아요 감소처리 가능"
    )
    // 좋아요 취소
    @DeleteMapping("/{id}/like")
    public ResponseEntity<Void> updateUnLikeMissingPost(@PathVariable Integer id, @RequestParam Integer userId) {
        missingLikeService.updateUnLikeMissingPost(id, userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "실종신고 게시글에 댓글 등록",
            description = "새로운 실종신고 게시글에 댓글을 등록합니다."
    )
    // 댓글 작성
    @PostMapping("/{id}/comments")
    public ResponseEntity<Integer> createMissingPostComment(@PathVariable Integer id,
                                                            @RequestBody CreateMissingCommentRequest request) {
        request.setPostId(id);
        return ResponseEntity.ok(missingCommentService.createMissingPostComment(request));
    }

    @Operation(
            summary = "실종신고 댓글 수정",
            description = "실종신고 게시글에 작성되어 있는 댓글을 댓글 작성자 본인만 수정합니다."
    )

    // 댓글 수정
    @PutMapping("/comments/{id}")
    public ResponseEntity<Void> updateMissingPostComment(@PathVariable Integer id,
                                                         @RequestParam Integer userId,
                                                         @RequestBody String content) {
        missingCommentService.updateMissingPostComment(id, userId, content);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "실종신고 댓글 삭제",
            description = "실종신고 게시글에 작성되어 있는 댓글을 댓글 작성자 본인만 삭제가능합니다. 댓글 삭제 : soft delete"
    )
    // 댓글 삭제
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteMissingPostComment(@PathVariable Integer id,
                                                         @RequestParam Integer userId) {
        missingCommentService.deleteMissingPostComment(id, userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "물품기부 게시글 게시글 신고(일반 사용자)",
            description = "물품기부 게시글을 신고합니다. 중복신고는 불가능합니다."
    )
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


    @Operation(
            summary = "물품기부 게시글 신고(보호소장)",
            description = "물품기부 게시글을 신고합니다. 중복신고는 불가능합니다."
    )
    // 보호소장 게시글 신고
    @PostMapping("/{postId}/report/head")
    public ResponseEntity<Void> createReportMissingPostByHead(@PathVariable Integer postId,
                                                 @RequestParam ReportCategory category,
                                                 @RequestParam(required = false) String detail,
                                                 @RequestParam Integer headId) {
        ShelterheadEntity head = new ShelterheadEntity();
        head.setId(headId);

        missingPostReportService.createReportMissingPostByHead(postId, category, detail, head);
        return ResponseEntity.ok().build();
    }



    @Operation(
            summary = "물품기부 게시글 댓글 신고(일반 사용자)",
            description = "물품기부 게시글에 등록되어있는 댓글을 신고합니다. 중복신고는 불가능합니다."
    )
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

    @Operation(
            summary = "실종신고 게시글 댓글 신고(보호소장)",
            description = "실종신고 게시글에 등록되어있는 댓글을 신고합니다. 중복신고는 불가능합니다."
    )
    // 보호소장 댓글 신고
    @PostMapping("/comments/{commentId}/report/head")
    public ResponseEntity<Void> createReportMissingPostCommentByHead(@PathVariable Integer commentId,
                                                    @RequestParam ReportCategory category,
                                                    @RequestParam(required = false) String detail,
                                                    @RequestParam Integer headId) {
        ShelterheadEntity head = new ShelterheadEntity();
        head.setId(headId); //

        missingPostCommentReportService.createReportMissingPostCommentByHead(commentId, category, detail, head);
        return ResponseEntity.ok().build();
    }

}

