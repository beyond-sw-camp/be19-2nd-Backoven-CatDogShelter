package com.backoven.catdogshelter.domain.volunteer.query.dto;

import lombok.Data;

@Data
public class VolunteerAssociationsListDTO {
    private int id;
    private String title;
    private String createdAt;
    private String startDate;
    private String detailAddress;
    private String companyName;
}
