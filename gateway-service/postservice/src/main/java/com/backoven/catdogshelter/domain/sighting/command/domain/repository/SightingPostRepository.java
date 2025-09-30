package com.backoven.catdogshelter.domain.sighting.command.domain.repository;

/* 설명. JPA를 위한 인터페이스(<테이블 entity, PK 타입>) */


import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.entity.SightingPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SightingPostRepository extends JpaRepository<SightingPost, Integer> {

}
