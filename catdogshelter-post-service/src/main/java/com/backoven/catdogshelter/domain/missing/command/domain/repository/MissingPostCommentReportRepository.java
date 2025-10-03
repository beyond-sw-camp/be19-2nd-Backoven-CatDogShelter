package com.backoven.catdogshelter.domain.missing.command.domain.repository;

import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPostComment;
import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPostCommentReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissingPostCommentReportRepository extends JpaRepository<MissingPostCommentReport, Long> {
    boolean existsByCommentAndUser(MissingPostComment comment, UserEntity user);

    boolean existsByCommentAndHead(MissingPostComment comment, ShelterheadEntity head);
}