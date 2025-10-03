// NoticeUpdateDTO.java (수정)
package com.backoven.catdogshelter.domain.notice.command.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class NoticeUpdateDTO {
    private String title;
    private String content;
    private List<NoticeFileDTO> files; // null이면 파일 무변경, []이면 모두 삭제, 값 있으면 전체 교체
}
