package com.backoven.catdogshelter.domain.donation.query.service;


import com.backoven.catdogshelter.domain.donation.query.dto.DonationPostCommentDTO;
import com.backoven.catdogshelter.domain.donation.query.dto.DonationPostQueryDTO;
import com.backoven.catdogshelter.domain.donation.query.mapper.DonationPostQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationPostQueryService {
    private final DonationPostQueryMapper mapper;

    public List<DonationPostQueryDTO> selectDonationPostSearch(String keyword, int page, int size) {
        int limit = size;
        int offset = (page - 1) * size;
        return mapper.selectDonationPostSearch(keyword, limit, offset);
    }

    public List<DonationPostQueryDTO> selectAllDonationPostsByView(int limit) {
        return mapper.selectAllDonationPostsByView(limit);
    }

    public List<DonationPostQueryDTO> selectAllDonationPostsByLiked(int limit) {
        return mapper.selectAllDonationPostsByLiked(limit);
    }

    public List<DonationPostQueryDTO> selectAllDonationPostsLatest(int limit) {
        return mapper.selectAllDonationPostsLatest(limit);
    }

    public void increaseView(Integer id) {
        mapper.increaseView(id);
    }


    // 게시판보드, 게시글 목록 조회 쿼리
    public List<DonationPostQueryDTO> selectAllDonationPosts() {
        return mapper.selectAllDonationPosts();
    }

    // 물품기부 게시글 내용 상세 조회
    public DonationPostQueryDTO selectDonationPostDetail(Integer postId) {
        // 조회수 증가
        mapper.increaseView((Integer) postId);

        // 상세 조회
        DonationPostQueryDTO dto = mapper.selectDonationPostDetail(postId);
        if (dto == null) {
            throw new RuntimeException("해당 게시글을 찾을 수 없습니다.");
        }
        return dto;
    }

    // 댓글 API 처리 (페이징)
    public List<DonationPostCommentDTO> selectDonationPostDetailComments(int postId, int page, int size) {
        int offset = (page - 1) * size;
        return mapper.selectDonationPostDetailComments(postId, offset, size);
    }

    // 댓글 총 개수 조회
    public int getTotalCount(int postId) {
        return mapper.countComments(postId);
    }

}
