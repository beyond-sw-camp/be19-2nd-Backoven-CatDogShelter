package com.backoven.catdogshelter.domain.sighting.command.domain.repository;


import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.entity.SightingPostCommentReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SightingPostCommentReportRepository extends JpaRepository<SightingPostCommentReport, Integer> {
    List<SightingPostCommentReport> findByCommentId(int commentId);
}
