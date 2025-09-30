package com.backoven.catdogshelter.domain.user.command.domain.repository;

import com.backoven.catdogshelter.common.entity.SigunguEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userSigunguRepository")
public interface SigunguRepository extends JpaRepository<SigunguEntity, Integer> {
}
