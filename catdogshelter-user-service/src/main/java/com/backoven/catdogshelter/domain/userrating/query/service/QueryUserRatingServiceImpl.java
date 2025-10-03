package com.backoven.catdogshelter.domain.userrating.query.service;

import com.backoven.catdogshelter.domain.userrating.query.dto.ratingDTO;
import com.backoven.catdogshelter.domain.userrating.query.dto.UserRatingDTO;
import com.backoven.catdogshelter.domain.userrating.query.mapper.RatingMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryUserRatingServiceImpl implements QueryUserRatingService {

    private final RatingMapper ratingMapper;

    public QueryUserRatingServiceImpl(RatingMapper ratingMapper) {
        this.ratingMapper = ratingMapper;
    }

    @Override
    public List<ratingDTO> findRating() {
        return ratingMapper.selectRating();
    }

    @Override
    public UserRatingDTO findUserRating(int userId) {
        return ratingMapper.selectUserRating(userId);

    }
}
