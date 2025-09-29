package com.backoven.catdogshelter.domain.adoption.command.domain.repository;//package com.backoven.catdogshelter.domain.adoption.command.domain.repository;

/* 설명. JPA를 위한 인터페이스(<테이블 entity, PK 타입>) */
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.entity.AdoptionPost.AdoptionPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository
        extends JpaRepository<AdoptionPostEntity, Integer> {

}
