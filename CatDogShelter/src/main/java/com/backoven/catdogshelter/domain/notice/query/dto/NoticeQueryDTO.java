package com.backoven.catdogshelter.domain.notice.query.dto;

import lombok.Data;

@Data
public class NoticeQueryDTO {
    private int id;
    private String title;
    private String createdAt;
    private String writer;
}
