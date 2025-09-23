package com.backoven.catdogshelter.domain.missing.query.mapper;

import com.backoven.catdogshelter.domain.missing.query.dto.MissingPostCommentDTO;
import com.backoven.catdogshelter.domain.missing.query.dto.MissingPostQueryDTO;
import com.backoven.catdogshelter.domain.missing.query.dto.MissingPostQueryDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MissingPostQueryMapper {
    // REQ-021 게시글 목록 조회 (검색 + 페이징)
    List<MissingPostQueryDTO> searchPosts(@Param("keyword") String keyword,
                                          @Param("offset") int offset,
                                          @Param("limit") int limit);

    // REQ-029 조회수 기준 인기 게시글
    List<MissingPostQueryDTO> findPopularPostsByView(@Param("limit") int limit);
    // REQ-030 추천수 기준 인기 게시글
    List<MissingPostQueryDTO> findPopularPostsByLike(@Param("limit") int limit);

    // REQ-031 최신 게시글 조회
    List<MissingPostQueryDTO> findLatestPosts(@Param("limit") int limit);

    // REQ-028 조회수 증가
    void increaseView(@Param("id") Long id);

    // 게시글 조회(view 증가)
    MissingPostQueryDTO selectPostById(Long id);

    //게시판보드, 게시글 목록 조회 쿼리
    List<MissingPostQueryDTO> findAllPostsWithLikeCount();

    //실종신고 게시글 상세내용 조회(게시글 하나 안에서)
    MissingPostQueryDetailDTO findPostDetail(int postId);

    //댓글 목록 (페이징 처리)
    List<MissingPostCommentDTO> findComments(@Param("postId") int postId,
                                             @Param("offset") int offset,
                                             @Param("limit") int limit);

    int countComments(@Param("postId") int postId);
}
