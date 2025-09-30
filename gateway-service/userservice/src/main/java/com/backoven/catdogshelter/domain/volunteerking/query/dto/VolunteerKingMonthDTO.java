package com.backoven.catdogshelter.domain.volunteerking.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VolunteerKingMonthDTO {
    private int year;
    private int month;
    private int volunteerTime;

    private String userName;
    private String ratingName;
}
