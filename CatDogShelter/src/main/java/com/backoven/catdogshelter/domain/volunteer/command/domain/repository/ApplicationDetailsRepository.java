package com.backoven.catdogshelter.domain.volunteer.command.domain.repository;

import com.backoven.catdogshelter.domain.volunteer.command.domain.aggregate.entity.ApplicationDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationDetailsRepository
        extends JpaRepository<ApplicationDetailsEntity, Long> {

}
