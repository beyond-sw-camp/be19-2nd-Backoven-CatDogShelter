package com.backoven.catdogshelter.domain.volunteer.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VolunteerPostDTO {
    private int id;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;
    private int view;
    private boolean isBlinded;
    private boolean isDeleted;
    private int volunteerId;
}
