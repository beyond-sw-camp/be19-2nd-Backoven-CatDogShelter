package com.backoven.catdogshelter.domain.userrating.query.controller;

import com.backoven.catdogshelter.domain.userrating.query.dto.ratingDTO;
import com.backoven.catdogshelter.domain.userrating.query.dto.UserRatingDTO;
import com.backoven.catdogshelter.domain.userrating.query.service.QueryUserRatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-rating")
@Tag(name = "등급 API")
public class QueryUserRatingController {

    private final QueryUserRatingService queryUserRatingService;

    @Autowired
    public QueryUserRatingController(QueryUserRatingService queryUserRatingService) {
        this.queryUserRatingService = queryUserRatingService;
    }

    @GetMapping("rating")
    @Operation(summary = "등급 확인", description = "서버에 있는 등급들을 확인")
    public List<ratingDTO> findRating() {
        return queryUserRatingService.findRating();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "유저 등급 확인", description = "유저가 가지는 등급을 확인")
    public UserRatingDTO findUserRating(@PathVariable int userId) {
        return queryUserRatingService.findUserRating(userId);
    }
}
