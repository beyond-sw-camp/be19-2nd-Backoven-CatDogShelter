package com.catdogshelter.repository.rating;

import com.catdogshelter.domain.user.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
