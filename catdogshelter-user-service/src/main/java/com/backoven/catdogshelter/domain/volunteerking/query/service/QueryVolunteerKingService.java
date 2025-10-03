package com.backoven.catdogshelter.domain.volunteerking.query.service;

import com.backoven.catdogshelter.domain.volunteerking.query.dto.VolunteerKingMonthDTO;
import com.backoven.catdogshelter.domain.volunteerking.query.dto.VolunteerKingTotalDTO;

import java.util.List;

public interface QueryVolunteerKingService {
    List<VolunteerKingMonthDTO> findVolunteerKingMonth();

    List<VolunteerKingTotalDTO> findVolunteerKingTotal();

    List<VolunteerKingMonthDTO> findVolunteerKingByUserId(int userId);
}
