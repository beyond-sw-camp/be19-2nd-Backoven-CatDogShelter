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
    private final DonationPostReportCommandService donationReportService;
    private final DonationPostReportCommentCommandService donationPostCommentReportService;
    private final DonationPostFileService donationPostFileService;

    // 게시글 생성
    @PostMapping
    public ResponseEntity<Long> createPost(@RequestBody CreateDonationPostRequest request) {
        return ResponseEntity.ok(donationPostService.createPost(request));
    }


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

    // 게시글 작성자 (보호소장)ceo_name으로 조회(조회>mybatis에서 다 구현했음)
    @GetMapping("/by-ceo")
    public ResponseEntity<List<DonationPostResponse>> getPostsByCeoName(@RequestParam String ceoName) {
        return ResponseEntity.ok(
                donationPostService.getPostsByCeoName(ceoName)
                        .stream()
                        .map(DonationPostResponse::new)
                        .toList()
        );
    }


    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable Long id,
                                           @RequestBody UpdateDonationPostRequest request) {
        request.setPostId(id);
        donationPostService.updatePost(request);
        return ResponseEntity.ok().build();
    }

    // 게시글 삭제 (작성자 본인만 가능)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id,
                                           @RequestParam Long headId) { // 작성자(보호소장) ID
        donationPostService.deletePost(id, headId);
        return ResponseEntity.noContent().build(); // 204
    }

    //특정 게시글에 댓글 작성
    @PostMapping("/{id}/comments")
    public ResponseEntity<Long> addComment(@PathVariable Long id,
                                           @RequestBody CreateDonationCommentRequest request) {
        request.setPostId(id); // PathVariable로 받은 게시글 ID를 DTO에 주입
        return ResponseEntity.ok(donationCommentService.createComment(request));
        // 댓글 저장 후 생성된 댓글 ID 반환
    }

    // 특정 댓글 삭제 (작성자 본인만 가능)
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id,
                                              @RequestParam Long userId) {
        donationCommentService.deleteComment(id, userId);
        return ResponseEntity.noContent().build(); // 204 반환 권장
    }

    // 좋아요 누르기
    @PostMapping("/{id}/like")
    public ResponseEntity<Void> likePost(@PathVariable Long id, @RequestParam Long userId) {
        donationLikeService.likePost(id, userId);
        return ResponseEntity.ok().build();
    }


    // 좋아요 취소
    @DeleteMapping("/{id}/like")
    public ResponseEntity<Void> unlikePost(@PathVariable Long id, @RequestParam Long userId) {
        donationLikeService.unlikePost(id, userId);
        return ResponseEntity.noContent().build(); // 204
    }

    //특정 게시글에 첨부파일(이미지 등) 업로드
    @PostMapping(value = "/{id}/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadFiles(@PathVariable Long id,
                                            @RequestParam("files") List<MultipartFile> files) {
        donationPostFileService.uploadFiles(id, files);
        return ResponseEntity.ok().build();
    }


    // 게시글 신고
    @PostMapping("/{id}/report")
    public ResponseEntity<Void> reportPost(@PathVariable Long id,
                                           @RequestParam ReportCategory category,
                                           @RequestParam(required = false) String detail,
                                           @RequestParam Long userId) {
        UserEntity user = new UserEntity();
        user.setId(userId); // 더미 (추후 UserService 연동)

        donationReportService.reportPost(id, category, detail, user);
        return ResponseEntity.ok().build();
    }

    // 댓글 신고
    @PostMapping("/comments/{id}/report")
    public ResponseEntity<Void> reportComment(@PathVariable Long id,
                                              @RequestParam ReportCategory category,
                                              @RequestParam(required = false) String detail,
                                              @RequestParam Long userId) {
        UserEntity user = new UserEntity();
        user.setId(userId);
        donationPostCommentReportService.reportComment(id, category, detail, user);
        return ResponseEntity.ok().build();
    }
}


