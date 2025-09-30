package com.backoven.catdogshelter.domain.donation.query.controller;

import com.backoven.catdogshelter.domain.donation.query.dto.DonationPostCommentDTO;
import com.backoven.catdogshelter.domain.donation.query.dto.DonationPostQueryDTO;
import com.backoven.catdogshelter.domain.donation.query.service.DonationPostQueryService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/donation-posts/query")
@RequiredArgsConstructor
@Tag(name = "물품기부 게시글 조회 API")
public class DonationPostQueryController {

    private final DonationPostQueryService service;

    @Operation(
            summary = "게시글 키워드 검색",
            description = "게시글을 키워드로 검색합니다."
    )
    //게시글 서치 조회
    @GetMapping("/search")
    public List<DonationPostQueryDTO> selectDonationPostSearch(@RequestParam(required = false) String keyword,
                                                 @RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        return service.selectDonationPostSearch(keyword, page, size);
    }
    @Operation(
            summary = "조회순 게시글 목록 조회",
            description = "조회 수 기준으로 정렬된 게시글 목록을 조회합니다."
    )
    //조회수 기준 인기글
    @GetMapping("/popular/view")
    public List<DonationPostQueryDTO> selectAllDonationPostsByView(
            @RequestParam(defaultValue = "10") int limit) {
        return service.selectAllDonationPostsByView(limit);
    }

    @Operation(
            summary = "추천순 게시글 목록 조회",
            description = "추천 수 기준으로 정렬된 게시글 목록을 조회합니다."
    )
    // 추천수 기준 인기글
    @GetMapping("/popular/like")
    public List<DonationPostQueryDTO> selectAllDonationPostsByLiked(
            @RequestParam(defaultValue = "10") int limit) {
        return service.selectAllDonationPostsByLiked(limit);
    }

    @Operation(
            summary = "최신순 게시글 목록 조회",
            description = "작성 최신순 기준으로 정렬된 게시글 목록을 조회합니다."
    )
    //최신 게시글
    @GetMapping("/latest")
    public List<DonationPostQueryDTO> selectAllDonationPostsLatest(@RequestParam(defaultValue = "5") int limit) {
        return service.selectAllDonationPostsLatest(limit);
    }

    //조회 수 증가
    @PostMapping("/{id}/view")
    public void increaseView(@PathVariable Integer id) {
        service.increaseView(id);
    }

    @Operation(
            summary = "게시글 목록 조회(게시판보드)",
            description = "게시글 목록을 조회한다."
    )
    // 게시판보드, 게시글 목록 조회 쿼리
    @GetMapping("/posts")
    public List<DonationPostQueryDTO> selectAllDonationPosts() {
        return service.selectAllDonationPosts();
    }

    @Operation(
            summary = "게시글 상세 내용 조회",
            description = "게시글의 상세 내용을 조회한다."
    )
    // 물품기부 게시글 내용 상세 조회
    @GetMapping("/posts/{postId}")
    public DonationPostQueryDTO selectDonationPostDetail(@PathVariable int postId) {
        return service.selectDonationPostDetail(postId);
    }

    @Operation(
            summary = "댓글 조회",
            description = "게시글의 댓글을 조회한다."
    )
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
