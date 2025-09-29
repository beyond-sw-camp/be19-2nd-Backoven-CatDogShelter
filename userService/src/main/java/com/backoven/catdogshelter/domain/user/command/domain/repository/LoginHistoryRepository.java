package com.backoven.catdogshelter.domain.user.command.domain.repository;

import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.LoginHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistoryEntity, Integer> {
}
