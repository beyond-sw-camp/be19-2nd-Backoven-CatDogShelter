package com.backoven.catdogshelter.domain.volunteer.command.domain.repository;

import com.backoven.catdogshelter.common.entity.SigunguEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerSigunguRepository extends JpaRepository<SigunguEntity, Integer> {
}
