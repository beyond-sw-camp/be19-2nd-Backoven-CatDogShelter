package com.backoven.catdogshelter.domain.volunteer.query.mapper;

import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerAssociationDTO;
import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerAssociationsListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VolunteerAssociationQueryMapper {
    List<VolunteerAssociationsListDTO> selectVolunteerAssociationList();

    VolunteerAssociationDTO selectOneVolunteerAssociation(int id);

    List<VolunteerAssociationsListDTO> selectVolunteerAssociationsByKeyword(String keyword);
}
