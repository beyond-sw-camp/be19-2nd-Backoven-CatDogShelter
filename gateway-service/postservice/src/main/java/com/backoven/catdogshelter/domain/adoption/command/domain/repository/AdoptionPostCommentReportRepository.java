package com.backoven.catdogshelter.domain.adoption.command.domain.repository;

import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.entity.AdoptionPost.AdoptionPostCommentReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionPostCommentReportRepository extends JpaRepository<AdoptionPostCommentReportEntity,Integer> {
}
