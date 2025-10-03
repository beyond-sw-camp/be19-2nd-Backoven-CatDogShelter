package com.backoven.catdogshelter.domain.volunteer.query.service;

import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerAssociationQueryDTO;
import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerAssociationSearchCond;

import java.util.List;

public interface VolunteerAssociationQueryService {

    VolunteerAssociationQueryDTO selectVolunteerAssociation(Integer id);

    List<VolunteerAssociationQueryDTO> selectVolunteerAssociationsBySearch(VolunteerAssociationSearchCond cond);
}
