package com.backoven.catdogshelter.domain.adoption.query.mapper;
import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostDetailQueryDTO;
import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostAllQueryDTO;
import com.backoven.catdogshelter.domain.adoption.query.dynamic.SearchCriteria;

import java.util.List;


public interface AdoptionMapper {

    // 전체 게시판 목록 조회
    List<AdoptionPostAllQueryDTO> selectAllAdoptionPosts();
    // 게시글 조회
    AdoptionPostDetailQueryDTO selectAdoptionPostById(int adoptionPostId);
    // 조회수 증가 쿼리 (+게시글 조회마다 증가)
    int updateAdoptionPostView(int adoptionPostId);
    // 조회수순 조회
    List<AdoptionPostAllQueryDTO> selectAllAdoptionPostsByView();
    // 추천수순 조회
    List<AdoptionPostAllQueryDTO> selectAllAdoptionPostByLiked();
    // 키워드 조회
    List<AdoptionPostAllQueryDTO> selectAdoptionPostByKeyword(SearchCriteria condition);
    // 컨디션 조회
    List<AdoptionPostAllQueryDTO> selectAdoptionPostByAnimalCondition(SearchCriteria animalCodition);
}
