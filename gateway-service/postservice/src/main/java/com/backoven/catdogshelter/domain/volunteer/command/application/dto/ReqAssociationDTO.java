package com.backoven.catdogshelter.domain.volunteer.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqAssociationDTO {
    private String title;
    private String content;
    private Integer time;
    private String startDate;
    private String detailAddress;
    private Integer numberOfPeople;
    private Long headId;
    private Integer sigunguId;
}
