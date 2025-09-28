package com.backoven.catdogshelter.domain.donation.command.domain.repository;

import com.backoven.catdogshelter.domain.donation.command.domain.aggregate.entity.DonationPostFiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationPostFilesRepository extends JpaRepository<DonationPostFiles, Integer> {}
