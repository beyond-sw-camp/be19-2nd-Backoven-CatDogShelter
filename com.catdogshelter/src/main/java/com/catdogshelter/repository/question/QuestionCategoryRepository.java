package com.catdogshelter.repository.question;

import com.catdogshelter.domain.user.QuestionCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionCategoryRepository extends JpaRepository<QuestionCategory, Integer> {
}
