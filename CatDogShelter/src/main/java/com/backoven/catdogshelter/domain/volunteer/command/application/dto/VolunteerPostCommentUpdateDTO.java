package com.backoven.catdogshelter.domain.volunteer.command.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VolunteerPostCommentUpdateDTO {
    private String content;
}