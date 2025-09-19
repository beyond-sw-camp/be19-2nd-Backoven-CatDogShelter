package com.catdogshelter.repository.donation;

import com.catdogshelter.domain.donation.DonationPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationPostRepository extends JpaRepository<DonationPost, Integer> {
}
