package com.backoven.catdogshelter.domain.post.command.domain.repository;


import com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
}
