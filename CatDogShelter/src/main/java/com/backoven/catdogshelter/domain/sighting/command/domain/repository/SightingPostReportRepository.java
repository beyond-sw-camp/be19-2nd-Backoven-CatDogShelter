package com.backoven.catdogshelter.domain.sighting.command.domain.repository;

import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.entity.SightingPostReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SightingPostReportRepository extends JpaRepository<SightingPostReport, Integer> {
}
