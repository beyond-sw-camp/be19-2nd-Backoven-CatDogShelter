package com.backoven.catdogshelter.domain.post.query.mapper;



import com.backoven.catdogshelter.domain.post.query.dto.PostInventoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostCreatedAtDescMapper {
    List<PostInventoryDTO> selectPostCreatedAtDesc();
}
