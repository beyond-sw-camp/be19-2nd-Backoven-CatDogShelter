package com.backoven.catdogshelter.domain.donation.command.application.controller;


import com.backoven.catdogshelter.common.util.ReportCategory;
import com.backoven.catdogshelter.domain.donation.command.application.dto.CreateDonationCommentRequest;
import com.backoven.catdogshelter.domain.donation.command.application.dto.CreateDonationPostRequest;
import com.backoven.catdogshelter.domain.donation.command.application.dto.DonationPostResponse;
import com.backoven.catdogshelter.domain.donation.command.application.dto.UpdateDonationPostRequest;
import com.backoven.catdogshelter.domain.donation.command.application.service.*;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPost;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/donation-posts")
@RequiredArgsConstructor
public class DonationExController {

    private final DonationPostCommandService donationPostService;
    private final DonationPostCommentCommandService donationCommentService;
    private final DonationPostLikeCommandService donationLikeService;
    private final DonationPostReportCommentCommandService donationPostCommentReportService;
    private final DonationPostFileService donationPostFileService;

      /*===============JPA - READ=============== */

    // 게시글 단건 조회(조회>mybatis에서 다 구현했음)
    @GetMapping("/{id}")
    public ResponseEntity<DonationPostResponse> getPost(@PathVariable Long id) {
        DonationPost post = donationPostService.getPost(id);
        return ResponseEntity.ok(new DonationPostResponse(post)); // DTO 변환
    }


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

    // 게시글 생성 (보호소장만 가능) 제목+내용+파일업로드
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> createDonationPost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("headId") Long headId,
            @RequestPart(value = "files", required = false) List<MultipartFile> files) {

        CreateDonationPostRequest request = new CreateDonationPostRequest();
        request.setTitle(title);
        request.setContent(content);
        request.setHeadId(headId);


        Long postId = donationPostService.createDonationPost(request, files);
        return ResponseEntity.ok(postId);
    }

    //게시글 수정 (본인만 가능) 파일 삭제,첨부 가능
    @PutMapping(value = "/{postId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateDonationPost(
            @PathVariable Long postId,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam Long headId,
            @RequestPart(value = "files", required = false) List<MultipartFile> files) {

        UpdateDonationPostRequest dto = new UpdateDonationPostRequest();
        dto.setPostId(postId);
        dto.setTitle(title);
        dto.setContent(content);

        donationPostService.updateDonationPost(dto, headId, files);
        return ResponseEntity.ok().build();
    }


    // 게시글 삭제 (작성자 본인만 가능)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonationPost(@PathVariable Long id,
                                           @RequestParam Long headId) { // 작성자(보호소장) ID
        donationPostService.deleteDonationPost(id, headId);
        return ResponseEntity.noContent().build(); // 204
    }


    // 이미지 다운로드(조회용)
    @GetMapping("/image/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
        byte[] imageData = donationPostFileService.downloadImage(fileName); // 로컬에서 파일 읽기
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG) // 확장자에 따라 변경 가능
                .body(imageData);
    }



    // 좋아요 누르기
    @PostMapping("/{id}/like")
    public ResponseEntity<Void> updateLikeDonationPost(@PathVariable Long id, @RequestParam Long userId) {
        donationLikeService.updateLikeDonationPost(id, userId);
        return ResponseEntity.ok().build();
    }


    // 좋아요 취소
    @DeleteMapping("/{id}/like")
    public ResponseEntity<Void> updateUnLikeDonationPost(@PathVariable Long id, @RequestParam Long userId) {
        donationLikeService.updateUnLikeDonationPost(id, userId);
        return ResponseEntity.noContent().build(); // 204
    }



    //특정 게시글에 댓글 작성
    @PostMapping("/{id}/comments")
    public ResponseEntity<Long> createDonationPostComment(@PathVariable Long id,
                                           @RequestBody CreateDonationCommentRequest request) {
        request.setPostId(id); // PathVariable로 받은 게시글 ID를 DTO에 주입
        return ResponseEntity.ok(donationCommentService.createDonationPostComment(request));
        // 댓글 저장 후 생성된 댓글 ID 반환
    }


    // 댓글 수정 (작성자 본인만 가능)
    @PutMapping("/comments/{id}")
    public ResponseEntity<Void> updateDonationPostComment(@PathVariable Long id,
                                              @RequestParam Long userId, // 댓글 작성자 ID
                                              @RequestBody String content) {
        donationCommentService.updateDonationPostComment(id, userId, content);
        return ResponseEntity.ok().build();
    }



    // 댓글 삭제 (작성자 본인만 가능)
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteDonationPostComment(@PathVariable Long id,
                                              @RequestParam Long userId) {
        donationCommentService.deleteDonationPostComment(id, userId);
        return ResponseEntity.noContent().build(); // 204 반환 권장
    }




    // 댓글 신고
    @PostMapping("/comments/{id}/report")
    public ResponseEntity<Void> createReportDonationPostComment(@PathVariable Long id,
                                              @RequestParam ReportCategory category,
                                              @RequestParam(required = false) String detail,
                                              @RequestParam Long userId) {
        UserEntity user = new UserEntity();
        user.setId(userId);
        donationPostCommentReportService.createReportDonationPostComment(id, category, detail, user);
        return ResponseEntity.ok().build();
    }




}


