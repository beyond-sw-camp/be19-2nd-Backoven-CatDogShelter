package com.backoven.catdogshelter.domain.donation.command.application.controller;


import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.common.enumulation.ReportCategory;
import com.backoven.catdogshelter.domain.donation.command.application.dto.CreateDonationCommentRequest;
import com.backoven.catdogshelter.domain.donation.command.application.dto.CreateDonationPostRequest;
import com.backoven.catdogshelter.domain.donation.command.application.dto.DonationPostResponse;
import com.backoven.catdogshelter.domain.donation.command.application.dto.UpdateDonationPostRequest;
import com.backoven.catdogshelter.domain.donation.command.application.service.*;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPost;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/donation-posts")
@RequiredArgsConstructor
@Tag(name = "물품기부 게시글 CUD API")
public class DonationExController {

    private final DonationPostCommandService donationPostService;
    private final DonationPostCommentCommandService donationCommentService;
    private final DonationPostLikeCommandService donationLikeService;
    private final DonationPostReportCommentCommandService donationPostCommentReportService;
    private final DonationPostFileService donationPostFileService;

      /*===============JPA - READ=============== */

    @Operation(
            summary = "물품기부 게시글 단건 조회",
            description = "물품기부 게시글을 1개 조회합니다"
    )
    // 게시글 단건 조회(조회>mybatis에서 다 구현했음)
    @GetMapping("/{id}")
    public ResponseEntity<DonationPostResponse> getPost(@PathVariable Integer id) {
        DonationPost post = donationPostService.getPost(id);
        return ResponseEntity.ok(new DonationPostResponse(post)); // DTO 변환
    }

    @Operation(
            summary = "물품기부 게시글 전체 조회",
            description = "품기부 게시글을 전체 조회합니다."
    )
    // 게시글 전체 조회(조회>mybatis에서 다 구현했음)
    @GetMapping
    public ResponseEntity<List<DonationPostResponse>> getAllPosts() {
        return ResponseEntity.ok(
                donationPostService.getAllPosts()
                        .stream()
                        .map(DonationPostResponse::new)
                        .toList()
        );
    }
    @Operation(
            summary = "물품기부 게시글 작성자 조회",
            description = "물품기부 게시글 작성자를 조회합니다. 물품기부 게시글 작성은 보호소장만 할 수 있습니다."
    )
    // 게시글 작성자 (보호소장)ceo_name으로 조회
    @GetMapping("/by-ceo")
    public ResponseEntity<List<DonationPostResponse>> getPostsByCeoName(@RequestParam String ceoName) {
        return ResponseEntity.ok(
                donationPostService.getPostsByCeoName(ceoName)
                        .stream()
                        .map(DonationPostResponse::new)
                        .toList()
        );
    }

    /* =============== JPA - CUD =============== */

    @Operation(
            summary = "물품기부 게시글 등록",
            description = "새로운 물품기부 게시글을 등록합니다."
    )
    // 게시글 생성 (보호소장만 가능) 제목+내용+파일업로드
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Integer> createDonationPost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("headId") Integer headId,
            @RequestPart(value = "files", required = false) List<MultipartFile> files) {

        CreateDonationPostRequest request = new CreateDonationPostRequest();
        request.setTitle(title);
        request.setContent(content);
        request.setHeadId(headId);


        Integer postId = donationPostService.createDonationPost(request, files);
        return ResponseEntity.ok(postId);
    }

    @Operation(
            summary = "물품기부 게시글 수정",
            description = "입력값을 id로 하는 게시글의 내용을 글 작성자만 수정합니다. 파일이 들어오면 기존 파일을 전체 삭제 후 새로 들어온 파일을 등록합니다."
    )
    //게시글 수정 (본인만 가능) 파일 삭제,첨부 가능
    @PutMapping(value = "/{postId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateDonationPost(
            @PathVariable Integer postId,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam Integer headId,
            @RequestPart(value = "files", required = false) List<MultipartFile> files) {

        UpdateDonationPostRequest dto = new UpdateDonationPostRequest();
        dto.setPostId(postId);
        dto.setTitle(title);
        dto.setContent(content);

        donationPostService.updateDonationPost(dto, headId, files);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "물품기부 게시글 삭제",
            description = "새입력값을 id로 하는 게시글을 글 작성자만 삭제합니다. 게시글: soft delete"
    )
    // 게시글 삭제 (작성자 본인만 가능)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonationPost(@PathVariable Integer id,
                                           @RequestParam Integer headId) { // 작성자(보호소장) ID
        donationPostService.deleteDonationPost(id, headId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "이미지 다운로드 조회용(테스트)",
            description = "이미지 다운로드 조회용 테스트 기능입니다."
    )
    // 이미지 다운로드(조회용)
    @GetMapping("/image/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
        byte[] imageData = donationPostFileService.downloadImage(fileName); // 로컬에서 파일 읽기
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG) // 확장자에 따라 변경 가능
                .body(imageData);
    }


    @Operation(
            summary = "물품기부 게시글 좋아요 증가",
            description = "물품기부 게시글 좋아요 증가처리, 게시글 하나에 한 회원이 중복 증가 불가능"
    )
    // 좋아요 누르기
    @PostMapping("/{id}/like")
    public ResponseEntity<Void> updateLikeDonationPost(@PathVariable Integer id, @RequestParam Integer userId) {
        donationLikeService.updateLikeDonationPost(id, userId);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "물품기부 게시글 좋아요 감소",
            description = "물품기부 게시글 좋아요 감소처리, 본인이 좋아요 누른 게시글에만 좋아요 감소처리 가능"
    )
    // 좋아요 취소
    @DeleteMapping("/{id}/like")
    public ResponseEntity<Void> updateUnLikeDonationPost(@PathVariable Integer id, @RequestParam Integer userId) {
        donationLikeService.updateUnLikeDonationPost(id, userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "물품기부 게시글에 댓글 등록",
            description = "새로운 물품기부 게시글에 댓글을 등록합니다."
    )
    //특정 게시글에 댓글 작성
    @PostMapping("/{id}/comments")
    public ResponseEntity<Integer> createDonationPostComment(@PathVariable Integer id,
                                           @RequestBody CreateDonationCommentRequest request) {
        request.setPostId(id); // PathVariable로 받은 게시글 ID를 DTO에 주입
        return ResponseEntity.ok(donationCommentService.createDonationPostComment(request));
        // 댓글 저장 후 생성된 댓글 ID 반환
    }

    @Operation(
            summary = "물품기부 댓글 수정",
            description = "물품기부 게시글에 작성되어 있는 댓글을 댓글 작성자 본인만 수정합니다."
    )
    // 댓글 수정 (작성자 본인만 가능)
    @PutMapping("/comments/{id}")
    public ResponseEntity<Void> updateDonationPostComment(@PathVariable Integer id,
                                              @RequestParam Integer userId, // 댓글 작성자 ID
                                              @RequestBody String content) {
        donationCommentService.updateDonationPostComment(id, userId, content);
        return ResponseEntity.ok().build();
    }


    @Operation(
            summary = "물품기부 댓글 삭제",
            description = "물품기부 게시글에 작성되어 있는 댓글을 댓글 작성자 본인만 삭제가능합니다. 댓글 삭제 : soft delete"
    )
    // 댓글 삭제 (작성자 본인만 가능)
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteDonationPostComment(@PathVariable Integer id,
                                              @RequestParam Integer userId) {
        donationCommentService.deleteDonationPostComment(id, userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "물품기부 게시글 댓글 신고(일반 사용자)",
            description = "물품기부 게시글에 등록되어있는 댓글을 신고합니다. 중복신고는 불가능합니다."
    )
    // 일반 사용자 댓글 신고
    @PostMapping("/{commentId}/report/user")
    public ResponseEntity<Void> createReportDonationPostCommentByUser(@PathVariable Integer commentId,
                                                    @RequestParam ReportCategory category,
                                                    @RequestParam(required = false) String detail,
                                                    @RequestParam Integer userId) {
        UserEntity user = new UserEntity();
        user.setUserId(userId);

        donationPostCommentReportService.createReportDonationPostCommentByUser(commentId, category, detail, user);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "물품기부 게시글 댓글 신고(보호소장)",
            description = "물품기부 게시글에 등록되어있는 댓글을 신고합니다. 중복신고는 불가능합니다."
    )
    // 보호소장 댓글 신고
    @PostMapping("/{commentId}/report/head")
    public ResponseEntity<Void> createReportDonationPostCommentByHead(@PathVariable Integer commentId,
                                                    @RequestParam ReportCategory category,
                                                    @RequestParam(required = false) String detail,
                                                    @RequestParam Integer headId) {
        ShelterheadEntity head = new ShelterheadEntity();
        head.setId(headId);

        donationPostCommentReportService.createReportDonationPostCommentByHead(commentId, category, detail, head);
        return ResponseEntity.ok().build();
    }




}


