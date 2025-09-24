package com.backoven.catdogshelter.domain.missing.query.controller;

import com.backoven.catdogshelter.domain.missing.query.dto.MissingPostCommentDTO;
import com.backoven.catdogshelter.domain.missing.query.dto.MissingPostQueryDTO;
import com.backoven.catdogshelter.domain.missing.query.dto.MissingPostQueryDetailDTO;
import com.backoven.catdogshelter.domain.missing.query.service.MissingPostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/missing-posts")
@RequiredArgsConstructor
public class MissingPostQueryController {
    private final MissingPostQueryService service;

    //REQ-021게시글 서치 조회
    @GetMapping("/search")
    public List<MissingPostQueryDTO> selectMissingPostSearch(@RequestParam(required = false) String keyword,
                                                 @RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        return service.selectMissingPostSearch(keyword, page, size);
    }

    //REQ-029 조회수 기준 인기글
    @GetMapping("/popular/view")
    public List<MissingPostQueryDTO> selectAllMissingPostsByView(
            @RequestParam(defaultValue = "10") int limit) {
        return service.selectAllMissingPostsByView(limit);
    }

    //REQ-030 추천수 기준 인기글
    @GetMapping("/popular/like")
    public List<MissingPostQueryDTO> selectAllMissingPostsByLiked(
            @RequestParam(defaultValue = "10") int limit) {
        return service.selectAllMissingPostsByLiked(limit);
    }

    //REQ-031 최신 작성 게시글
    @GetMapping("/latest")
    public List<MissingPostQueryDTO> selectAllMissingPostsLatest(@RequestParam(defaultValue = "5") int limit) {
        return service.selectAllMissingPostsLatest(limit);
    }

    //REQ-028 조회 수 증가(Post 테스트용)
    @PostMapping("/{id}/view")
    public void increaseView(@PathVariable Long id) {
        service.increaseView(id);
    }


    // 게시판보드, 게시글 목록 조회 쿼리
    @GetMapping("/posts")
    public List<MissingPostQueryDTO> selectAllMissingPosts() {
        return service.selectAllMissingPosts();
    }

    // 실종신고 게시글 내용 상세 조회

    @GetMapping("/posts/{postId}")
    public MissingPostQueryDetailDTO selectMissingPostDetail(@PathVariable int postId) {
        return service.selectMissingPostDetail(postId);
    }

    
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
