package com.backoven.catdogshelter.domain.volunteer.command.application.dto;

import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class AssociationDTO {
    private Long Id;                    // Request에 안 담긴다.
    private String title;
    private String content;
    private String createdAt;           // Request에 안 담기지만 서버에서 바뀜.
    private Integer time;
    private String startDate;
    private String detailAddress;
    private Integer numberOfPeople;
    private boolean deadLine;           // Request에 안 담긴다.
    private boolean isEnd;              // Request에 안 담긴다.
    private Long headId;
    private Integer sigunguId;
}
