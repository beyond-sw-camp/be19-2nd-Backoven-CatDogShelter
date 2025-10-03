package com.backoven.catdogshelter.common.repository;

import com.backoven.catdogshelter.common.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);

    UserEntity findByUserAccount(String userAccount);
}
