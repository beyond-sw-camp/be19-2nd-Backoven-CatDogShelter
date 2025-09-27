package com.backoven.catdogshelter.domain.donation.query.controller;

import com.backoven.catdogshelter.domain.donation.query.dto.DonationPostCommentDTO;
import com.backoven.catdogshelter.domain.donation.query.dto.DonationPostQueryDTO;
import com.backoven.catdogshelter.domain.donation.query.service.DonationPostQueryService;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/donation-posts/query")
@RequiredArgsConstructor
public class DonationPostQueryController {

    private final DonationPostQueryService service;

    //REQ-021게시글 서치 조회
    @GetMapping("/search")
    public List<DonationPostQueryDTO> selectDonationPostSearch(@RequestParam(required = false) String keyword,
                                                 @RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        return service.selectDonationPostSearch(keyword, page, size);
    }

    //REQ-029 조회수 기준 인기글
    @GetMapping("/popular/view")
    public List<DonationPostQueryDTO> selectAllDonationPostsByView(
            @RequestParam(defaultValue = "10") int limit) {
        return service.selectAllDonationPostsByView(limit);
    }

    //REQ-030 추천수 기준 인기글
    @GetMapping("/popular/like")
    public List<DonationPostQueryDTO> selectAllDonationPostsByLiked(
            @RequestParam(defaultValue = "10") int limit) {
        return service.selectAllDonationPostsByLiked(limit);
    }

    //REQ-031 최신 게시글
    @GetMapping("/latest")
    public List<DonationPostQueryDTO> selectAllDonationPostsLatest(@RequestParam(defaultValue = "5") int limit) {
        return service.selectAllDonationPostsLatest(limit);
    }

    //REQ-028 조회 수 증가
    @PostMapping("/{id}/view")
    public void increaseView(@PathVariable Long id) {
        service.increaseView(id);
    }



    // 게시판보드, 게시글 목록 조회 쿼리
    @GetMapping("/posts")
    public List<DonationPostQueryDTO> selectAllDonationPosts() {
        return service.selectAllDonationPosts();
    }

    // 물품기부 게시글 내용 상세 조회
    @GetMapping("/posts/{postId}")
    public DonationPostQueryDTO selectDonationPostDetail(@PathVariable int postId) {
        return service.selectDonationPostDetail(postId);
    }

    // 댓글 API 처리
    @GetMapping("/posts/{postId}/comments")
    public Map<String, Object> selectDonationPostDetailComments(@PathVariable int postId,
                                                                @RequestParam(defaultValue = "1") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        // 댓글 목록 조회
        List<DonationPostCommentDTO> comments = service.selectDonationPostDetailComments(postId, page, size);

        // 댓글 총 개수 조회
        int totalCount = service.getTotalCount(postId);

        // 응답 구성
        Map<String, Object> response = new HashMap<>();
        response.put("postId", postId);
        response.put("page", page);
        response.put("size", size);
        response.put("totalCount", totalCount);
        response.put("comments", comments);

        return response;
    }

}
