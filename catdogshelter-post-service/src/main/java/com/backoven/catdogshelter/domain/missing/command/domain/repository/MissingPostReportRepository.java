package com.backoven.catdogshelter.domain.missing.command.domain.repository;
import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPost;
import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPostReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissingPostReportRepository extends JpaRepository<MissingPostReport, Integer> {
    boolean existsByPostAndUser(MissingPost post, UserEntity user);
    boolean existsByPostAndHead(MissingPost post, ShelterheadEntity head);
}