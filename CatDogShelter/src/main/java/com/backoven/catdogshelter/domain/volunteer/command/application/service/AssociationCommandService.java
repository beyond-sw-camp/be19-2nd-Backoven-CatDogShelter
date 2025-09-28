package com.backoven.catdogshelter.domain.volunteer.command.application.service;

// NOTE: Empty skeleton; add @Transactional use cases and orchestrate domain service calls.

import com.backoven.catdogshelter.domain.volunteer.command.application.dto.ReqAssociationDTO;

public interface AssociationCommandService {
    void writeAssociation(ReqAssociationDTO newAssociation);

    void modifyAssociation(ReqAssociationDTO newAssociation, Long associationId);
}
