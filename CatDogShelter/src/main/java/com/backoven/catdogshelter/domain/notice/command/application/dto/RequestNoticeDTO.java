package com.backoven.catdogshelter.domain.notice.command.application.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class RequestNoticeDTO {
    private String title;
    private String content;

    List<MultipartFile> files;
}
