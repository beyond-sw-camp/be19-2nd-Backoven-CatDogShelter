package com.backoven.catdogshelter.domain.volunteerking.query.mapper;

import com.backoven.catdogshelter.domain.volunteerking.query.dto.VolunteerKingMonthDTO;
import com.backoven.catdogshelter.domain.volunteerking.query.dto.VolunteerKingTotalDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VolunteerKingMapper {
    List<VolunteerKingMonthDTO> selectVolunteerKingMonth();

    List<VolunteerKingTotalDTO> selectVolunteerKingTotal();

    List<VolunteerKingMonthDTO> selectVolunteerKingByUserId(int userId);
}
