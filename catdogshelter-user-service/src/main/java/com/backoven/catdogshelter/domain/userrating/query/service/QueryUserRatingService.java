package com.backoven.catdogshelter.domain.userrating.query.service;

import com.backoven.catdogshelter.domain.userrating.query.dto.ratingDTO;
import com.backoven.catdogshelter.domain.userrating.query.dto.UserRatingDTO;

import java.util.List;

public interface QueryUserRatingService {
    List<ratingDTO> findRating();

    UserRatingDTO findUserRating(int userId);
}
