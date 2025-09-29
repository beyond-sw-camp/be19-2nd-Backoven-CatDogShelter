package com.backoven.catdogshelter.domain.missing.command.domain.repository;

import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPost;
import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPostLiked;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissingPostLikedRepository extends JpaRepository<MissingPostLiked, Integer> {
    boolean existsByPostAndUser(MissingPost post, UserEntity user);
    void deleteByPostAndUser(MissingPost post, UserEntity user);
}