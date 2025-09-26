package com.backoven.catdogshelter.domain.sighting.command.domain.repository;


import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.entity.SightingPostCommentReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SightingPostCommentReportRepository extends JpaRepository<SightingPostCommentReport, Integer> {
}
