package com.catdogshelter.repository.sighting;

import com.catdogshelter.domain.sighting.SightingPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SightingPostRepository extends JpaRepository<SightingPost, Integer> {
}
