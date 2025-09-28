package com.backoven.catdogshelter.domain.adoption.command.domain.repository;//package com.backoven.catdogshelter.domain.adoption.command.domain.repository;

/* 설명. JPA를 위한 인터페이스(<테이블 entity, PK 타입>) */
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.entity.AdoptionPost.AdoptionPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<<< HEAD:CatDogShelter/src/main/java/com/backoven/catdogshelter/domain/adoption/command/domain/repository/AdoptionPostRepository.java
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.entity.AdoptionPost.AdoptionPostEntity;
========
>>>>>>>> d848345 (Feature/user (#99)):CatDogShelter/src/main/java/com/backoven/catdogshelter/domain/adoption/command/domain/repository/AdoptionRepository.java

public interface AdoptionPostRepository
        extends JpaRepository<AdoptionPostEntity, Integer> {

}
