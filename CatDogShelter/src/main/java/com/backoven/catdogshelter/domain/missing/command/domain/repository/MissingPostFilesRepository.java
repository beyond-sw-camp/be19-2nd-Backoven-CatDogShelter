package com.backoven.catdogshelter.domain.missing.command.domain.repository;


import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPostFiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissingPostFilesRepository extends JpaRepository<MissingPostFiles, Integer> {
}