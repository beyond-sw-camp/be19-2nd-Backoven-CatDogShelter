package com.catdogshelter.repository.adoption;

import com.catdogshelter.domain.adoption.AdoptionPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionPostRepository extends JpaRepository<AdoptionPost, Integer> {
}
