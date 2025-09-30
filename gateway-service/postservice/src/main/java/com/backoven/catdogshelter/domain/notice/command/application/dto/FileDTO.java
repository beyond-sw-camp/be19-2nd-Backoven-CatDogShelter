package com.backoven.catdogshelter.domain.notice.command.application.dto;

import lombok.Data;

@Data
public class FileDTO {
    private String fileRename;
    private String filePath;
    private String uploadedAt;
}