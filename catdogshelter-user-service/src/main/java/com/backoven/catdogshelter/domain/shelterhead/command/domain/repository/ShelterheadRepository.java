package com.backoven.catdogshelter.domain.shelterhead.command.domain.repository;

import com.backoven.catdogshelter.domain.shelterhead.command.domain.aggregate.entity.ShelterheadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterheadRepository extends JpaRepository<ShelterheadEntity, Integer> {
    ShelterheadEntity findByHeadAccount(String headAccount);
}
