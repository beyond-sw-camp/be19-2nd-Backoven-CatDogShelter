package com.backoven.catdogshelter.domain.adoption.query.service;

import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostDetailQueryDTO;
import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostAllQueryDTO;
import com.backoven.catdogshelter.domain.adoption.query.dynamic.SearchCriteria;

import java.util.List;

public interface AdoptionPostQueryService {

    List<AdoptionPostAllQueryDTO> selectAdoptionAllPosts();
    AdoptionPostDetailQueryDTO selectAdoptionPostById(int adoptionPostId);
    List<AdoptionPostAllQueryDTO> selectAdoptionPostByKeyword(SearchCriteria keyword);
    List<AdoptionPostAllQueryDTO> selectAdoptionPostByAnimalCondition(SearchCriteria animalCondition);

    List<AdoptionPostAllQueryDTO> selectAdoptionAllPostsByView();

    List<AdoptionPostAllQueryDTO> selectAdoptionAllPostsByLiked();
}
