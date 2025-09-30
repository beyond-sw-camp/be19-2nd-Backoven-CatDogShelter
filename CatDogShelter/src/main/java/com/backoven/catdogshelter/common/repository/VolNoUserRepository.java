package com.backoven.catdogshelter.common.repository;

import com.backoven.catdogshelter.common.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolNoUserRepository extends JpaRepository<UserEntity, Integer> {
}
