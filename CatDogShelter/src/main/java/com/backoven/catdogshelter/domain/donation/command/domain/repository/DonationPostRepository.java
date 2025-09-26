package com.backoven.catdogshelter.domain.donation.command.domain.repository;

import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationPostRepository extends JpaRepository<DonationPost, Long> {}