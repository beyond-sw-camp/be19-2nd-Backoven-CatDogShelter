// NoticeDTO.java (작성)
package com.backoven.catdogshelter.domain.notice.command.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class NoticeDTO {
    private String title;
    private String content;
    private List<NoticeFileDTO> files; // null 가능
}
