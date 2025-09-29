package com.backoven.catdogshelter.domain.missing.command.domain.repository;

import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPostComment;

import org.springframework.data.jpa.repository.JpaRepository;



public interface MissingPostCommentRepository extends JpaRepository<MissingPostComment, Integer> {

}