package com.backoven.catdogshelter.domain.post.query.mapper;

import com.backoven.catdogshelter.domain.post.query.dto.PostDetailDTO;
import com.backoven.catdogshelter.domain.post.query.dto.PostInventoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostDetailMapper {
    PostDetailDTO selectPostDetail(@Param("id") int id);
}
