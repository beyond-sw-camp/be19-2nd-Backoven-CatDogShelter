package com.backoven.catdogshelter.domain.message.command.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestMessageDTO {
    private String content;
    private Integer subjectNumber;
    private Integer subjectUserId;
    private Integer sendUserId;
    private Integer subjectHeadId;
    private Integer sendHeadId;
}
