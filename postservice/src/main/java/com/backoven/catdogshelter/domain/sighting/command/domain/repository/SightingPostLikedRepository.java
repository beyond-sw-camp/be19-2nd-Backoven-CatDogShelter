package com.backoven.catdogshelter.domain.sighting.command.domain.repository;

import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.entity.SightingPostLiked;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SightingPostLikedRepository extends JpaRepository<SightingPostLiked, Integer> {
}
