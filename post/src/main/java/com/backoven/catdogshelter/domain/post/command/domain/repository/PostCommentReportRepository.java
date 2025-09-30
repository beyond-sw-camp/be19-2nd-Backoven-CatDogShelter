package com.backoven.catdogshelter.domain.post.command.domain.repository;

import com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity.PostCommentReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentReportRepository
        extends JpaRepository<PostCommentReportEntity, Integer> {
    boolean existsByCommentIdAndUserId(int commentId, Integer userId);
    boolean existsByCommentIdAndHeadId(int commentId, Integer headId);
}
