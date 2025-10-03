package com.backoven.catdogshelter.domain.volunteer.command.domain.repository;

import com.backoven.catdogshelter.domain.volunteer.command.domain.aggregate.entity.VolunteerPostCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VolunteerPostCommentRepository extends JpaRepository<VolunteerPostCommentEntity, Integer> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update VolunteerPostCommentEntity c set c.deleted = true, c.updatedAt = :now where c.id = :id and c.deleted = false")
    int softDelete(@Param("id") Integer id, @Param("now") String now);
}
