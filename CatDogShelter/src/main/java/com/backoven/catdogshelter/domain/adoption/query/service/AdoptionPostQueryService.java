package com.backoven.catdogshelter.domain.adoption.query.service;

import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostDetailDTO;
import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostQueryDTO;

import java.util.List;
import java.util.Map;

public interface AdoptionPostQueryService {

    List<AdoptionPostQueryDTO> selectAdoptionAllPosts();
    AdoptionPostDetailDTO selectAdoptionPostById(int adoptionPostId);
    List<AdoptionPostQueryDTO> selectAdoptionPostByKeyword(String keyword);
    List<AdoptionPostQueryDTO> selectAdoptionPostByAnimalCondition(Map<String, String> animalCodition);
}
