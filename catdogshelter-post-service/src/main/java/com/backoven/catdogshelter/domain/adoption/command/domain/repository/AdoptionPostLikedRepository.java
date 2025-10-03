package com.backoven.catdogshelter.domain.adoption.command.domain.repository;

import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.entity.AdoptionPost.AdoptionPostEntity;
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.entity.AdoptionPost.AdoptionPostLikedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionPostLikedRepository extends JpaRepository<AdoptionPostLikedEntity, Integer> {
    boolean existsByAdoptionPostAndUser(AdoptionPostEntity adoptionPost, UserEntity user);
    boolean existsByAdoptionPostAndShelterHead(AdoptionPostEntity adoptionPost, ShelterheadEntity shelterHead);

    void deleteByAdoptionPostAndUser(AdoptionPostEntity adoptionPost, UserEntity user);
    void deleteByAdoptionPostAndShelterHead(AdoptionPostEntity adoptionPost, ShelterheadEntity shelterHead);

    int countByAdoptionPost(AdoptionPostEntity adoptionPost);
}
