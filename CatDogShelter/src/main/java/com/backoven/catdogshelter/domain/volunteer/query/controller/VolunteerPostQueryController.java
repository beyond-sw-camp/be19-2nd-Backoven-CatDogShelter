// VolunteerPost 컨트롤러
package com.backoven.catdogshelter.domain.volunteer.query.controller;

import com.backoven.catdogshelter.domain.volunteer.query.service.VolunteerPostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/volunteer-posts")
public class VolunteerPostQueryController {

    private final VolunteerPostQueryService volunteerPostQueryService;

    // 전체 목록 (order: created | views | likes)  — 내림차순
    // 예: GET /api/volunteer-posts/list/views?page=1&size=10
    @GetMapping(value = {"/list/{order}"})
    public Map<String, Object> list(@PathVariable String order,
                                    @RequestParam(required = false) Integer page,
                                    @RequestParam(required = false) Integer size) {
        return volunteerPostQueryService.list(order, page, size);
    }

    // 검색 목록 (작성자/제목/내용 + keyword 통합)
    // 예: GET /api/volunteer-posts/search/likes?keyword=산책&page=1&size=10
    //     GET /api/volunteer-posts/search/created?title=청소&author=행복보호소
    @GetMapping("/search/{order}")
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
