package com.backoven.catdogshelter.domain.notice.query.dto;

import lombok.Data;

import java.util.List;

@Data
public class NoticePostQueryDTO {
    private int id;
    private String title;
    private String content;
    private String created;
    private String updated;
    private String writer;
    private int likes;

    private List<NoticePostFileQueryDTO> files;
}
