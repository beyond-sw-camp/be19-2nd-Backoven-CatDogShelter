package com.backoven.catdogshelter.domain.userrating.query.controller;

import com.backoven.catdogshelter.domain.userrating.query.dto.ratingDTO;
import com.backoven.catdogshelter.domain.userrating.query.dto.UserRatingDTO;
import com.backoven.catdogshelter.domain.userrating.query.service.QUserRatingService;
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
public class QUserRatingController {

    private final QUserRatingService qUserRatingService;

    @Autowired
    public QUserRatingController(@Qualifier("QUserRatingServiceImpl") QUserRatingService qUserRatingService) {
        this.qUserRatingService = qUserRatingService;
    }

    @GetMapping("rating")
    @Operation(summary = "등급 확인", description = "서버에 있는 등급들을 확인")
    public List<ratingDTO> findRating() {
        return qUserRatingService.findRating();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "유저 등급 확인", description = "유저가 가지는 등급을 확인")
    public UserRatingDTO findUserRating(@PathVariable int userId) {
        return qUserRatingService.findUserRating(userId);
    }
}
