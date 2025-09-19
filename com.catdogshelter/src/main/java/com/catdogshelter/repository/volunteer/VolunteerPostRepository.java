package com.catdogshelter.repository.volunteer;

import com.catdogshelter.domain.volunteer.VolunteerPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerPostRepository extends JpaRepository<VolunteerPost, Integer> {
}
