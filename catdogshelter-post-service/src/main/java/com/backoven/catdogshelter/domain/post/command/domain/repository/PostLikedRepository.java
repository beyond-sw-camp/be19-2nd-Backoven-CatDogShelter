package com.backoven.catdogshelter.domain.post.command.domain.repository;

import com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity.PostLikedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikedRepository extends JpaRepository<PostLikedEntity, Integer> {
    boolean existsByPostIdAndUserId(int postId, Integer userId);
    boolean existsByPostIdAndHeadId(int postId, Integer headId);
}
