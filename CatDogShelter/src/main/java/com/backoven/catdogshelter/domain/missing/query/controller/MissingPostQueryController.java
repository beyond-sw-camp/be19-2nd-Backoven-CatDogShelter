package com.backoven.catdogshelter.domain.missing.query.controller;

import com.backoven.catdogshelter.domain.missing.query.dto.MissingPostQueryDTO;
import com.backoven.catdogshelter.domain.missing.query.service.MissingPostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missing-posts")
@RequiredArgsConstructor
public class MissingPostQueryController {
    private final MissingPostQueryService service;

    @GetMapping("/search")
    public List<MissingPostQueryDTO> searchPosts(@RequestParam(required = false) String keyword,
                                                 @RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        return service.searchPosts(keyword, page, size);
    }

    @GetMapping("/popular")
    public List<MissingPostQueryDTO> getPopularPosts(@RequestParam(defaultValue = "5") int limit) {
        return service.getPopularPosts(limit);
    }

    @GetMapping("/latest")
    public List<MissingPostQueryDTO> getLatestPosts(@RequestParam(defaultValue = "5") int limit) {
        return service.getLatestPosts(limit);
    }

    @PostMapping("/{id}/view")
    public void increaseView(@PathVariable Long id) {
        service.increaseView(id);
    }

    @GetMapping("/{id}")
    public MissingPostQueryDTO getPostById(@PathVariable Long id) {
        service.increaseView(id); //조회수 증가 로직
        return service.getPostById(id);
    }
}
