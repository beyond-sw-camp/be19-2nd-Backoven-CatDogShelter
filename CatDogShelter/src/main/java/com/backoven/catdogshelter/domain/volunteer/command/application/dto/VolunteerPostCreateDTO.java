package com.backoven.catdogshelter.domain.volunteer.command.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class VolunteerPostCreateDTO {
    private String title;
    private String content;
    private Integer volappdetailId;
}