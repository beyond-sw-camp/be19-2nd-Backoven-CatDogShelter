package com.backoven.catdogshelter.domain.volunteer.query.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class VolunteerAssociationQueryDTO {
    private Integer id;
    private String title;
    private String content;
    private String createdAt;
    private String startDate;
    private Integer time;
    private String detailAddress;
    private Integer numberOfPeople;
    private Boolean deadline;
    private Boolean isEnd;

    private Integer headId;
    private String ceoName;
    private String companyName;

    // ✅ 추가
    private Integer sigunguId;
    private String sigunguName;
}
