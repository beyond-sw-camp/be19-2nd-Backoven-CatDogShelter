package com.backoven.catdogshelter.domain.volunteer.command.domain.repository;

import com.backoven.catdogshelter.common.entity.ShelterHeadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerShelterHeadRepository extends JpaRepository<ShelterHeadEntity, Long> {
}
