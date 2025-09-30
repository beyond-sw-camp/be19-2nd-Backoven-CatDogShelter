package com.backoven.catdogshelter.domain.volunteerking.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VolunteerKingTotalDTO {
    private int volunteerTime;

    private String userName;
    private String ratingName;
}
