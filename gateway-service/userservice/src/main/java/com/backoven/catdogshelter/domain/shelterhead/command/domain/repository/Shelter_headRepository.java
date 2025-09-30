package com.backoven.catdogshelter.domain.shelterhead.command.domain.repository;

import com.backoven.catdogshelter.domain.shelterhead.command.domain.aggregate.entity.Shelter_headEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Shelter_headRepository extends JpaRepository<Shelter_headEntity, Integer> {
    Shelter_headEntity findByHeadAccount(String headAccount);
}
