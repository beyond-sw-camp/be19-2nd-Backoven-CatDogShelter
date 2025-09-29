package com.backoven.userservice.user.command.domain.repository;

import com.backoven.catdogshelter.common.entity.QuestionCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionCategoryRepository extends JpaRepository<QuestionCategoryEntity, Integer> {
}
