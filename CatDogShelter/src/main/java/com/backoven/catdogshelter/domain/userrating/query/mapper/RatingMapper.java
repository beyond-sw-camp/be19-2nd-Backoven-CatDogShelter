package com.backoven.catdogshelter.domain.userrating.query.mapper;

import com.backoven.catdogshelter.domain.userrating.query.dto.UserRatingDTO;
import com.backoven.catdogshelter.domain.userrating.query.dto.ratingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RatingMapper {

    List<ratingDTO> selectRating();

    UserRatingDTO selectUserRating(int userId);
}
