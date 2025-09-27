package com.backoven.catdogshelter.domain.donation.command.domain.repository;

import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationPostReportRepository extends JpaRepository<DonationPostReport, Long> {
}