package com.backoven.catdogshelter.domain.notice.command.application.dto;

import lombok.Data;

@Data
public class ResponseNoticeDTO {
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;
}
