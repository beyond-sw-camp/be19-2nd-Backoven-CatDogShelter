package com.backoven.catdogshelter.domain.missing.query.mapper;

import com.backoven.catdogshelter.domain.missing.query.dto.MissingPostQueryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MissingPostQueryMapper {
    // REQ-021 게시글 목록 조회 (검색 + 페이징)
    List<MissingPostQueryDTO> searchPosts(@Param("keyword") String keyword,
                                          @Param("offset") int offset,
                                          @Param("limit") int limit);

    // REQ-029 인기 게시글 조회
    List<MissingPostQueryDTO> findPopularPosts(@Param("limit") int limit);

    // REQ-030 최신 게시글 조회
    List<MissingPostQueryDTO> findLatestPosts(@Param("limit") int limit);

    // REQ-028 조회수 증가
    void increaseView(@Param("id") Long id);

    // 게시글 조회
    MissingPostQueryDTO selectPostById(Long id);
}
