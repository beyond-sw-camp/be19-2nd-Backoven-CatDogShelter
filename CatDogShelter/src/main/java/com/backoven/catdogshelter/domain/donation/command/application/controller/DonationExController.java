package com.backoven.catdogshelter.domain.donation.command.application.controller;


import com.backoven.catdogshelter.common.entity.ShelterHeadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.common.util.ReportCategory;
import com.backoven.catdogshelter.domain.donation.command.application.dto.CreateDonationCommentRequest;
import com.backoven.catdogshelter.domain.donation.command.application.dto.CreateDonationPostRequest;
import com.backoven.catdogshelter.domain.donation.command.application.dto.DonationPostResponse;
import com.backoven.catdogshelter.domain.donation.command.application.dto.UpdateDonationPostRequest;
import com.backoven.catdogshelter.domain.donation.command.application.service.*;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPost;
import com.backoven.catdogshelter.domain.user.command.domain.repository.UserRepository;
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
    public ResponseEntity<DonationPostResponse> getPost(@PathVariable Integer id) {
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


    // 게시글 삭제 (작성자 본인만 가능)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonationPost(@PathVariable Integer id,
                                           @RequestParam Integer headId) { // 작성자(보호소장) ID
        donationPostService.deleteDonationPost(id, headId);
        return ResponseEntity.noContent().build();
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
    public ResponseEntity<Void> updateLikeDonationPost(@PathVariable Integer id, @RequestParam Integer userId) {
        donationLikeService.updateLikeDonationPost(id, userId);
        return ResponseEntity.ok().build();
    }


    // 좋아요 취소
    @DeleteMapping("/{id}/like")
    public ResponseEntity<Void> updateUnLikeDonationPost(@PathVariable Integer id, @RequestParam Integer userId) {
        donationLikeService.updateUnLikeDonationPost(id, userId);
        return ResponseEntity.noContent().build();
    }



    //특정 게시글에 댓글 작성
    @PostMapping("/{id}/comments")
    public ResponseEntity<Integer> createDonationPostComment(@PathVariable Integer id,
                                           @RequestBody CreateDonationCommentRequest request) {
        request.setPostId(id); // PathVariable로 받은 게시글 ID를 DTO에 주입
        return ResponseEntity.ok(donationCommentService.createDonationPostComment(request));
        // 댓글 저장 후 생성된 댓글 ID 반환
    }


    // 댓글 수정 (작성자 본인만 가능)
    @PutMapping("/comments/{id}")
    public ResponseEntity<Void> updateDonationPostComment(@PathVariable Integer id,
                                              @RequestParam Integer userId, // 댓글 작성자 ID
                                              @RequestBody String content) {
        donationCommentService.updateDonationPostComment(id, userId, content);
        return ResponseEntity.ok().build();
    }



    // 댓글 삭제 (작성자 본인만 가능)
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteDonationPostComment(@PathVariable Integer id,
                                              @RequestParam Integer userId) {
        donationCommentService.deleteDonationPostComment(id, userId);
        return ResponseEntity.noContent().build();
    }


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

    // 보호소장 댓글 신고
    @PostMapping("/{commentId}/report/head")
    public ResponseEntity<Void> createReportDonationPostCommentByHead(@PathVariable Integer commentId,
                                                    @RequestParam ReportCategory category,
                                                    @RequestParam(required = false) String detail,
                                                    @RequestParam Integer headId) {
        ShelterHeadEntity head = new ShelterHeadEntity();
        head.setId(headId);

        donationPostCommentReportService.createReportDonationPostCommentByHead(commentId, category, detail, head);
        return ResponseEntity.ok().build();
    }




}


