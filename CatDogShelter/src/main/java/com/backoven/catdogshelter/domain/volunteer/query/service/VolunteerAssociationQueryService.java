package com.backoven.catdogshelter.domain.volunteer.query.service;

import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerAssociationDTO;
import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerAssociationsListDTO;
import com.backoven.catdogshelter.domain.volunteer.query.mapper.VolunteerAssociationQueryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VolunteerAssociationQueryService {

    private final VolunteerAssociationQueryMapper volunteerAssociationQueryMapper;

    @Autowired
    public VolunteerAssociationQueryService(VolunteerAssociationQueryMapper volunteerAssociationQueryMapper) {
        this.volunteerAssociationQueryMapper = volunteerAssociationQueryMapper;
    }

    public List<VolunteerAssociationsListDTO> getVolunteerAssociationList() {



        return volunteerAssociationQueryMapper.selectVolunteerAssociationList();
    }

    public VolunteerAssociationDTO getVolunteerAssociation(int id) {



        return volunteerAssociationQueryMapper.selectOneVolunteerAssociation(id);

    }

    public List<VolunteerAssociationsListDTO> selectVolunteerAssociationsByKeyword(String keyword) {
        return volunteerAssociationQueryMapper.selectVolunteerAssociationsByKeyword(keyword);

    }
}
