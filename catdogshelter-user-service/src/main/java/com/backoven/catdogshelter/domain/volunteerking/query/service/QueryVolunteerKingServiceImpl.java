package com.backoven.catdogshelter.domain.volunteerking.query.service;

import com.backoven.catdogshelter.domain.volunteerking.query.dto.VolunteerKingMonthDTO;
import com.backoven.catdogshelter.domain.volunteerking.query.dto.VolunteerKingTotalDTO;
import com.backoven.catdogshelter.domain.volunteerking.query.mapper.VolunteerKingMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryVolunteerKingServiceImpl implements QueryVolunteerKingService {

    private final VolunteerKingMapper volunteerKingMapper;

    public QueryVolunteerKingServiceImpl(VolunteerKingMapper volunteerKingMapper) {
        this.volunteerKingMapper = volunteerKingMapper;
    }

    @Override
    public List<VolunteerKingMonthDTO> findVolunteerKingMonth() {
        return volunteerKingMapper.selectVolunteerKingMonth();
    }

    @Override
    public List<VolunteerKingTotalDTO> findVolunteerKingTotal() {
        return volunteerKingMapper.selectVolunteerKingTotal();
    }

    @Override
    public List<VolunteerKingMonthDTO> findVolunteerKingByUserId(int userId) {
        return volunteerKingMapper.selectVolunteerKingByUserId(userId);
    }
}
