package com.backoven.catdogshelter.domain.post.query.mapper;

import com.backoven.catdogshelter.domain.post.query.dto.PostCommentReportDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostCommentReportDetailMapper {
    List<PostCommentReportDetailDTO> selectPostCommentReportDetail(@Param("postCommentId") int postCommentReportId );
}
