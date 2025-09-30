package com.backoven.catdogshelter.domain.missing.query.mapper;

import com.backoven.catdogshelter.domain.missing.query.dto.MissingPostCommentDTO;
import com.backoven.catdogshelter.domain.missing.query.dto.MissingPostQueryDTO;
import com.backoven.catdogshelter.domain.missing.query.dto.MissingPostQueryDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MissingPostQueryMapper {
    // 게시글 목록 조회 (검색 + 페이징)
    List<MissingPostQueryDTO> selectMissingPostSearch(@Param("keyword") String keyword,
                                          @Param("offset") int offset,
                                          @Param("limit") int limit);

    // 조회수 기준 인기 게시글
    List<MissingPostQueryDTO>   selectAllMissingPostsByView(@Param("limit") int limit);
    //추천수 기준 인기 게시글
    List<MissingPostQueryDTO> selectAllMissingPostsByLiked(@Param("limit") int limit);

    //  최신 게시글 조회
    List<MissingPostQueryDTO> selectAllMissingPostsLatest(@Param("limit") int limit);

    //게시판보드, 게시글 목록 조회 쿼리
    List<MissingPostQueryDTO> selectAllMissingPosts();


    // 조회수 증가
    void increaseView(@Param("id") Integer id);

    //실종신고 게시글 상세내용 조회(게시글 하나 안에서)
    MissingPostQueryDetailDTO selectMissingPostDetail(int postId);


    //댓글 목록 (페이징 처리)
    List<MissingPostCommentDTO> selectMissingPostDetailComments(@Param("postId") int postId,
                                             @Param("offset") int offset,
                                             @Param("limit") int limit);

    int countComments(@Param("postId") int postId);
}
