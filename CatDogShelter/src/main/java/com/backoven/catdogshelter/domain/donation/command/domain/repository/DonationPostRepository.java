package com.backoven.catdogshelter.domain.donation.command.domain.repository;

import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationPostRepository extends JpaRepository<DonationPost, Integer> {
    // head(보호소장) 이름으로 조회
    List<DonationPost> findByHeadCeoName(String ceoName);

}