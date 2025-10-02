package com.backoven.catdogshelter.domain.adoption.command.domain.repository;

import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.entity.AdoptionPost.AdoptionPostFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionPostFileRepository extends JpaRepository<AdoptionPostFileEntity, Integer> {
}
