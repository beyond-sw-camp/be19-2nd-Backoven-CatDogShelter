package com.backoven.catdogshelter.domain.volunteer.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {
    private Long id;
    private Boolean status;
    private Integer time;
    private Long volunteerId;
    private Long userId;
}
