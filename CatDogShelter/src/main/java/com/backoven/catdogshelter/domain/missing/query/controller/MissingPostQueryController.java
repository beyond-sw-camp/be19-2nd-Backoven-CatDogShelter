package com.backoven.catdogshelter.domain.missing.query.controller;

import com.backoven.catdogshelter.domain.missing.query.dto.MissingPostCommentDTO;
import com.backoven.catdogshelter.domain.missing.query.dto.MissingPostQueryDTO;
import com.backoven.catdogshelter.domain.missing.query.dto.MissingPostQueryDetailDTO;
import com.backoven.catdogshelter.domain.missing.query.service.MissingPostQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/missing-posts/query")
@RequiredArgsConstructor
@Tag(name = "실종신고 게시글 조회 API")
public class MissingPostQueryController {
    private final MissingPostQueryService service;

    @Operation(
            summary = "게시글 키워드 검색",
            description = "게시글을 키워드로 검색합니다."
    )
    //게시글 서치 조회
    @GetMapping("/search")
    public List<MissingPostQueryDTO> selectMissingPostSearch(@RequestParam(required = false) String keyword,
                                                 @RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        return service.selectMissingPostSearch(keyword, page, size);
    }
    @Operation(
            summary = "조회순 게시글 목록 조회",
            description = "조회 수 기준으로 정렬된 게시글 목록을 조회합니다."
    )
    //조회수 기준 인기글
    @GetMapping("/popular/view")
    public List<MissingPostQueryDTO> selectAllMissingPostsByView(
            @RequestParam(defaultValue = "10") int limit) {
        return service.selectAllMissingPostsByView(limit);
    }

    @Operation(
            summary = "추천순 게시글 목록 조회",
            description = "추천 수 기준으로 정렬된 게시글 목록을 조회합니다."
    )
    //추천수 기준 인기글
    @GetMapping("/popular/like")
    public List<MissingPostQueryDTO> selectAllMissingPostsByLiked(
            @RequestParam(defaultValue = "10") int limit) {
        return service.selectAllMissingPostsByLiked(limit);
    }
    @Operation(
            summary = "최신순 게시글 목록 조회",
            description = "작성 최신순 기준으로 정렬된 게시글 목록을 조회합니다."
    )
    //최신 작성 게시글
    @GetMapping("/latest")
    public List<MissingPostQueryDTO> selectAllMissingPostsLatest(@RequestParam(defaultValue = "5") int limit) {
        return service.selectAllMissingPostsLatest(limit);
    }

    //조회 수 증가(Post 테스트용)
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
    public List<MissingPostQueryDTO> selectAllMissingPosts() {
        return service.selectAllMissingPosts();
    }

    @Operation(
            summary = "게시글 상세 내용 조회",
            description = "게시글의 상세 내용을 조회한다."
    )
    // 실종신고 게시글 내용 상세 조회
    @GetMapping("/posts/{postId}")
    public MissingPostQueryDetailDTO selectMissingPostDetail(@PathVariable int postId) {
        return service.selectMissingPostDetail(postId);
    }

    @Operation(
            summary = "댓글 조회",
            description = "게시글의 댓글을 조회한다."
    )
    //댓글 api처리
    @GetMapping("/posts/{postId}/comments")
    public Map<String, Object> selectMissingPostDetailComments(@PathVariable int postId,
                                           @RequestParam(defaultValue = "1") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        List<MissingPostCommentDTO> comments = service.selectMissingPostDetailComments(postId, page, size);
        //해당 게시글의 전체 댓글 수를 가져옴(페이징 정보를 클라이언트에 내려주기 위해)
        int totalCount = service.getTotalCount(postId);
        //응답 구성
        Map<String, Object> response = new HashMap<>();
        response.put("postId", postId);
        response.put("page", page);
        response.put("size", size);
        response.put("totalCount", totalCount);
        response.put("comments", comments);
        return response;
    }
}
