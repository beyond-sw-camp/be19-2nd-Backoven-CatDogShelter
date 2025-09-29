package com.backoven.catdogshelter.domain.volunteer.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqApplicationDTO {
    private Boolean status;
    private Integer time;
    private Long volunteerId;
    private Long userId;
}
