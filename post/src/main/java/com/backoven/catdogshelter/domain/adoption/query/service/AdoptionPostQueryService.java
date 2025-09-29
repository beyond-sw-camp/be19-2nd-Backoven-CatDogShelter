package com.backoven.catdogshelter.domain.adoption.query.service;

import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostDetailQueryDTO;
import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostAllQueryDTO;
import com.backoven.catdogshelter.domain.adoption.query.dynamic.SearchCriteria;

import java.util.List;

public interface AdoptionPostQueryService {
    // 전체 게시판 목록 조회
    List<AdoptionPostAllQueryDTO> selectAdoptionAllPosts();
    // 게시글 조회
    AdoptionPostDetailQueryDTO selectAdoptionPostById(int adoptionPostId);
    // 조회수순 조회
    List<AdoptionPostAllQueryDTO> selectAdoptionAllPostsByView();
    // 추천수순 조회
    List<AdoptionPostAllQueryDTO> selectAdoptionAllPostsByLiked();
    // 키워드 조회
    List<AdoptionPostAllQueryDTO> selectAdoptionPostByKeyword(SearchCriteria keyword);
    // 컨디션 조회
    List<AdoptionPostAllQueryDTO> selectAdoptionPostByAnimalCondition(SearchCriteria animalCondition);
}
