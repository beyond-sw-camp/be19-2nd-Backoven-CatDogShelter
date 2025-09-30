// VolunteerAssociationUpdateDTO.java (수정용)
package com.backoven.catdogshelter.domain.volunteer.command.application.dto;

import lombok.Data;

@Data
public class VolunteerAssociationUpdateDTO {
    private String title;
    private String content;
    private Integer time;
    private String startDate;
    private String detailAddress;
    private Boolean deadline;
    private Integer numberOfPeople;
    private Boolean isEnd; // 모임 종료 처리도 수정에서 허용
}
