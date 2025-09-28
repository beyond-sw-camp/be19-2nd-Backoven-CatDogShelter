package com.backoven.catdogshelter.domain.notice.command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeUpdateDTO {
    private String title;
    private String content;
}
