package com.backoven.catdogshelter.domain.notice.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/* 설명. JPA를 위한 인터페이스(<테이블 entity, PK 타입>) */

public interface JpaRepositoryInterface extends JpaRepository<Integer, Integer> {
}
