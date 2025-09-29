package com.backoven.catdogshelter.domain.user.command.domain.repository;

import com.backoven.catdogshelter.common.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);
    List<UserEntity> findAllBySigunguId(Integer sigunguId);
    UserEntity findByUserAccount(String userAccount);
}
