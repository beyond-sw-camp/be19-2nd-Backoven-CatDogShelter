package com.backoven.catdogshelter.domain.sighting.command.infrastructure.repository;

import com.backoven.catdogshelter.domain.sighting.command.domain.entity.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

/* 설명. JPA를 위한 인터페이스(<테이블 entity, PK 타입>) */

public interface JpaRepositoryImpl extends JpaRepository<Entity, Integer> {
}
