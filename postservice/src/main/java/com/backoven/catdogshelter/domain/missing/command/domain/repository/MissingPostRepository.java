package com.backoven.catdogshelter.domain.missing.command.domain.repository;

import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissingPostRepository extends JpaRepository<MissingPost, Integer> {
    // head(보호소장) 이름으로 조회
    List<MissingPost> findByHeadCeoName(String ceoName);
}