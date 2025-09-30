package com.backoven.catdogshelter.domain.post.query.mapper;

import com.backoven.catdogshelter.domain.post.query.dto.PostLikedDescDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostLikedDescMapper {
    List<PostLikedDescDTO> selectPostLikedDesc();
}
