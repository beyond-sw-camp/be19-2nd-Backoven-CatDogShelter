package com.catdogshelter.repository.user;

import com.catdogshelter.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
