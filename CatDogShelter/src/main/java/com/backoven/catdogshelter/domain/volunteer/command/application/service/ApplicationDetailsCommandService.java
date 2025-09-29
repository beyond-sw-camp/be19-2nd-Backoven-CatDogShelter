package com.backoven.catdogshelter.domain.volunteer.command.application.service;

import com.backoven.catdogshelter.domain.volunteer.command.application.dto.ReqApplicationDTO;

public interface ApplicationDetailsCommandService {
    Long addApplicationDetail(Long volunteerId, ReqApplicationDTO requestDTO);

    void updateApplicationDetail(Long applicationId, ReqApplicationDTO requestDTO);
}
