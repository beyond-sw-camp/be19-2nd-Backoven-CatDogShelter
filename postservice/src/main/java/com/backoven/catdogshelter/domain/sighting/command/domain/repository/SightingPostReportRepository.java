package com.backoven.catdogshelter.domain.sighting.command.domain.repository;

import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.entity.SightingPostReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SightingPostReportRepository extends JpaRepository<SightingPostReport, Integer> {
    List<SightingPostReport> findByPostId(int postId);
}
