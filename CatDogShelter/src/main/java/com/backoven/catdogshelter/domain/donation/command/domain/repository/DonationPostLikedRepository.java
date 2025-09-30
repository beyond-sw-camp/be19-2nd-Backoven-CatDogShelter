package com.backoven.catdogshelter.domain.donation.command.domain.repository;

import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPost;
import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostLiked;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationPostLikedRepository extends JpaRepository<DonationPostLiked, Integer> {
    boolean existsByPostAndUser(DonationPost post, UserEntity user);
    void deleteByPostAndUser(DonationPost post, UserEntity user);
}