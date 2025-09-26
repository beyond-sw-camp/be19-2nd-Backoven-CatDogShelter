package com.backoven.catdogshelter.domain.donation.command.application.controller;


import com.backoven.catdogshelter.common.util.ReportCategory;
import com.backoven.catdogshelter.domain.donation.command.application.dto.CreateDonationCommentRequest;
import com.backoven.catdogshelter.domain.donation.command.application.dto.CreateDonationPostRequest;
import com.backoven.catdogshelter.domain.donation.command.application.dto.UpdateDonationPostRequest;
import com.backoven.catdogshelter.domain.donation.command.application.service.DonationPostCommandService;
import com.backoven.catdogshelter.domain.donation.command.application.service.DonationPostCommentCommandService;
import com.backoven.catdogshelter.domain.donation.command.application.service.DonationPostLikeCommandService;
import com.backoven.catdogshelter.domain.donation.command.application.service.DonationPostReportCommandService;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donation-posts")
@RequiredArgsConstructor
public class DonationExController {
    private final DonationPostCommandService donationPostService;
    private final DonationPostCommentCommandService donationCommentService;
    private final DonationPostLikeCommandService donationLikeService;
    private final DonationPostReportCommandService donationReportService;

    @PostMapping
    public ResponseEntity<Long> createPost(@RequestBody CreateDonationPostRequest request) {
        return ResponseEntity.ok(donationPostService.createPost(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable Long id,
                                           @RequestBody UpdateDonationPostRequest request) {
        request.setPostId(id);
        donationPostService.updatePost(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        donationPostService.deletePost(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Long> addComment(@PathVariable Long id,
                                           @RequestBody CreateDonationCommentRequest request) {
        request.setPostId(id);
        return ResponseEntity.ok(donationCommentService.createComment(request));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        donationCommentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Void> likePost(@PathVariable Long id, @RequestParam Long userId) {
        // user 조회는 생략 (실제 구현 시 UserService에서 가져와야 함)
        UserEntity user = new UserEntity();
        user.setId(userId);
        donationLikeService.likePost(id, user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/comments/{id}/report")
    public ResponseEntity<Void> reportComment(@PathVariable Long id,
                                              @RequestParam ReportCategory category,
                                              @RequestParam(required = false) String detail,
                                              @RequestParam Long userId) {
        UserEntity user = new UserEntity();
        user.setId(userId);
        donationReportService.reportComment(id, category, detail, user);
        return ResponseEntity.ok().build();
    }
}
