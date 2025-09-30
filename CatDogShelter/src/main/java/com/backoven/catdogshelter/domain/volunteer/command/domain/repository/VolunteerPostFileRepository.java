package com.backoven.catdogshelter.domain.volunteer.command.domain.repository;

import com.backoven.catdogshelter.domain.volunteer.command.domain.aggregate.entity.VolunteerPostFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolunteerPostFileRepository extends JpaRepository<VolunteerPostFileEntity, Integer> {
    List<VolunteerPostFileEntity> findByIdIn(List<Integer> ids);
    void deleteByIdIn(List<Integer> ids);
}
