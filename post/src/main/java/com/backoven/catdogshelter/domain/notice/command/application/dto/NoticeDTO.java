package com.backoven.catdogshelter.domain.notice.command.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class NoticeDTO {
    private Long id;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;
    private Long ratingId;

    private List<FileDTO> files;
}
