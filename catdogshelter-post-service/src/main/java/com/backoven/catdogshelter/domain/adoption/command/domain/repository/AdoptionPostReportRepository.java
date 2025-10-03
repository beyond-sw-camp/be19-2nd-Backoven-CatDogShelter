package com.backoven.catdogshelter.domain.adoption.command.domain.repository;

import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.entity.AdoptionPost.AdoptionPostReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionPostReportRepository extends JpaRepository<AdoptionPostReportEntity, Integer> {
}
