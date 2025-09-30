// VolunteerPost 컨트롤러
package com.backoven.catdogshelter.domain.volunteer.query.controller;

import com.backoven.catdogshelter.domain.volunteer.query.service.VolunteerPostQueryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/volunteer-posts")
public class VolunteerPostQueryController {

    private final VolunteerPostQueryService volunteerPostQueryService;

    @Autowired
    public VolunteerPostQueryController(VolunteerPostQueryService volunteerPostQueryService) {
        this.volunteerPostQueryService = volunteerPostQueryService;
    }

    @Operation(summary = "게시글 목록 조회",
            description = "조건에 맞는 전체 목록 조회(order: created | views | likes) 내림차순" +
                    "\n/volunteer-posts/list/views?page=1&size=10")
    @GetMapping(value = {"/list/{order}"})
    public Map<String, Object> list(@PathVariable String order,
                                    @RequestParam(required = false) Integer page,
                                    @RequestParam(required = false) Integer size) {
        return volunteerPostQueryService.list(order, page, size);
    }

    @Operation(summary = "게시글 전체 목록 조회", description = "게시판 이용자는 게시글 전체 목록을 페이지로 조회할 수 있다.")
    @GetMapping(value = {"/list", "/", "/search"})
    public Map<String, Object> list(@RequestParam(required = false) Integer page,
                                    @RequestParam(required = false) Integer size) {
        return volunteerPostQueryService.list(page, size);
    }

    @Operation(summary = "",
            description = "게시글 검색 목록 조회" +
                    "\n/volunteer-posts/search/likes?keyword=산책&page=1&size=10" +
                    "\n/volunteer-posts/search/created?title=청소&author=행복보호소" +
                    "\n제목/내용/작성자에 검색어를 포함한 모든 게시글을 목록으로 조회한다.")
    @GetMapping(value = {"/search/{order}"})
    public Map<String, Object> search(@PathVariable String order,
                                      @RequestParam(required = false) String keyword,
                                      @RequestParam(required = false) String title,
                                      @RequestParam(required = false) String content,
                                      @RequestParam(required = false) String author,
                                      @RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer size) {
        return volunteerPostQueryService.search(order, keyword, title, content, author, page, size);
    }
}
