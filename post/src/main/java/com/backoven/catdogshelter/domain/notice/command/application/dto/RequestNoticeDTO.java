package com.backoven.catdogshelter.domain.notice.command.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class RequestNoticeDTO {
    private String title;
    private String content;

    List<FileDTO> files;
}
