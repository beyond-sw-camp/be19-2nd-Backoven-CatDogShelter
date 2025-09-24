package com.backoven.catdogshelter.domain.post.query.mapper;

import com.backoven.catdogshelter.domain.post.query.dto.PostDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostDetailMapper {
    /* xml에서 가져온 id 파라미터 값을 int id에 주입 */
    PostDetailDTO selectPostDetail(@Param("id") int id);
}
