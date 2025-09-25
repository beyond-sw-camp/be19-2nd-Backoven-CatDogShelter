package com.backoven.catdogshelter.domain.adoption.query.mapper;
import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostDetailDTO;
import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostQueryDTO;

import java.util.List;
import java.util.Map;


public interface AdoptionMapper {
    List<AdoptionPostQueryDTO> selectAllAdoptionPosts();
    AdoptionPostDetailDTO selectAdoptionPostById(int adoptionPostId);
    List<AdoptionPostQueryDTO> selectAdoptionPostByKeyword(String condition);
    List<AdoptionPostQueryDTO> selectAdoptionPostByAnimalCondition(Map<String, String> animalCodition);
}
