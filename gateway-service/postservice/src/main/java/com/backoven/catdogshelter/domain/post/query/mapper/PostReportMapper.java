package com.backoven.catdogshelter.domain.post.query.mapper;

import com.backoven.catdogshelter.domain.post.query.dto.PostReportDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostReportMapper {
    List<PostReportDetailDTO> selectPostReportDetail(@Param("postId") int postId);
}
