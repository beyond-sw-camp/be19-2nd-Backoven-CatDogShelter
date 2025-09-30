package com.backoven.catdogshelter.domain.sighting.command.domain.repository;

import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.entity.SightingPostFiles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SightingPostFilesRepository extends JpaRepository<SightingPostFiles, Integer> {
    List<SightingPostFiles> findByPostId(int postId);
}
