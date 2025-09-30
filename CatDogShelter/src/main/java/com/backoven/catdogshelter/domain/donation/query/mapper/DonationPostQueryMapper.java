package com.backoven.catdogshelter.domain.donation.query.mapper;

import com.backoven.catdogshelter.domain.donation.query.dto.DonationPostCommentDTO;
import com.backoven.catdogshelter.domain.donation.query.dto.DonationPostQueryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DonationPostQueryMapper {
    // 게시글 목록 조회 (검색 + 페이징)
    List<DonationPostQueryDTO> selectDonationPostSearch(@Param("keyword") String keyword,
                                           @Param("limit") int limit,
                                           @Param("offset") int offset);

    //  조회수 기준 인기 게시글
    List<DonationPostQueryDTO> selectAllDonationPostsByView(@Param("limit") int limit);
    //  추천수 기준 인기 게시글
    List<DonationPostQueryDTO> selectAllDonationPostsByLiked(@Param("limit") int limit);

    //  최신 게시글 조회
    List<DonationPostQueryDTO> selectAllDonationPostsLatest(@Param("limit") int limit);

    //조회수 증가
    void increaseView(@Param("id") Integer id);

    List<DonationPostQueryDTO> selectAllDonationPosts();

    DonationPostQueryDTO selectDonationPostDetail(int postId);

    List<DonationPostCommentDTO> selectDonationPostDetailComments(int postId, int offset, int size);

    int countComments(int postId);
}
