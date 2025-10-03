package com.backoven.catdogshelter.domain.adoption.command.domain.repository;

import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionShelterHeadRepository extends JpaRepository<ShelterheadEntity, Integer> {
}
