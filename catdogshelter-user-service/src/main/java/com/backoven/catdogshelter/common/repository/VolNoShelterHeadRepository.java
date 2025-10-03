package com.backoven.catdogshelter.common.repository;

import com.backoven.catdogshelter.common.entity.ShelterHeadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolNoShelterHeadRepository extends JpaRepository<ShelterHeadEntity, Integer> {
}
