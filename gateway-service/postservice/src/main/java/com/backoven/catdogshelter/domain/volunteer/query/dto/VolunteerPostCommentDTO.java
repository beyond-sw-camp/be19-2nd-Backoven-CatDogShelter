package com.backoven.catdogshelter.domain.volunteer.query.dto;

import lombok.Data;

@Data
public class VolunteerPostCommentDTO {
    private int id;                 // 댓글 번호

    private String content;         // 내용

    private String createdAt;       // 작성일

    private String commentor;       // user or shelter company name
}
