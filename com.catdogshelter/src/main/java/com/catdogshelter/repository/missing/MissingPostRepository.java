package com.catdogshelter.repository.missing;

import com.catdogshelter.domain.missing.MissingPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissingPostRepository extends JpaRepository<MissingPost, Integer> {
}
