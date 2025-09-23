package com.backoven.catdogshelter.domain.missing.query.service;

import com.backoven.catdogshelter.domain.missing.query.dto.MissingPostCommentDTO;
import com.backoven.catdogshelter.domain.missing.query.dto.MissingPostQueryDTO;
import com.backoven.catdogshelter.domain.missing.query.dto.MissingPostQueryDetailDTO;
import com.backoven.catdogshelter.domain.missing.query.mapper.MissingPostQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissingPostQueryService {

    private final MissingPostQueryMapper mapper;
    // 게시판보드, 게시글 목록 조회 쿼리
    public List<MissingPostQueryDTO> getAllPostsWithLikeCount() {
        return mapper.findAllPostsWithLikeCount();
    }

    //REQ-021게시글 서치 조회
    public List<MissingPostQueryDTO> searchPosts(String keyword, int page, int size) {
        int offset = (page - 1) * size;
        return mapper.searchPosts(keyword, offset, size);
    }

    //REQ-029 조회수 기준 인기글
    public List<MissingPostQueryDTO> getPopularPostsByView(int limit) {
        return mapper.findPopularPostsByView(limit);
    }

    //REQ-030 추천수 기준 인기글
    public List<MissingPostQueryDTO> getPopularPostsByLike(int limit) {
        return mapper.findPopularPostsByLike(limit);
    }

    //REQ-031 최신 작성 게시글
    public List<MissingPostQueryDTO> getLatestPosts(int limit) {
        return mapper.findLatestPosts(limit);
    }

    //조회수 증가 로직
    public void increaseView(Long id) {
        mapper.increaseView(id);
    }

    //REQ-028 게시글 조회 하면 조회 수 증가
    public MissingPostQueryDTO getPostById(Long id) {
        MissingPostQueryDTO dto = mapper.selectPostById(id);
        if (dto == null) {
            throw new RuntimeException("해당 게시글을 찾을 수 없습니다.");
        }
        return dto;
    }

    // 실종신고 게시글 내용 상세 조회
    public MissingPostQueryDetailDTO getPostDetail(int postId) {
        return mapper.findPostDetail(postId);

    }

    //댓글 api처리
    public List<MissingPostCommentDTO> getComments(int postId, int page, int size) {
        int offset = (page - 1) * size;
        return mapper.findComments(postId, offset, size);
    }
    //해당 게시글의 전체 댓글 수를 가져옴(페이징 정보를 클라이언트에 내려주기 위해)
    public int getTotalCount(int postId) {
        return mapper.countComments(postId);
    }

}
