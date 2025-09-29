package com.backoven.catdogshelter.domain.sighting.command.domain.repository;

import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.entity.SightingPostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SightingPostCommentRepository extends JpaRepository<SightingPostComment, Integer> {
    List<SightingPostComment> findByPostId(int postId);
}
