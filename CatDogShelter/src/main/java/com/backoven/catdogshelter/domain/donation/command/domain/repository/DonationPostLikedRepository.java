package com.backoven.catdogshelter.domain.donation.command.domain.repository;

import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPost;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostLiked;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationPostLikedRepository extends JpaRepository<DonationPostLiked, Long> {
    boolean existsByPostAndUser(DonationPost post, UserEntity user);
}