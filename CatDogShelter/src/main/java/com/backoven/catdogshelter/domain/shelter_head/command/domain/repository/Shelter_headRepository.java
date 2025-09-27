package com.backoven.catdogshelter.domain.shelter_head.command.domain.repository;

import com.backoven.catdogshelter.domain.shelter_head.command.domain.aggregate.entity.Shelter_headEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Shelter_headRepository extends JpaRepository<Shelter_headEntity, Integer> {
}
