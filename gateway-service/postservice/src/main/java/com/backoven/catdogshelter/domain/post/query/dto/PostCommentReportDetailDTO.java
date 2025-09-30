package com.backoven.catdogshelter.domain.post.query.dto;

/* 신고 내역 조회를 위해 필요한 DTO */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostCommentReportDetailDTO {
    private int reportCount;
    private int id;
    private String category;
    private String etc_detail;
    private String created_at;
    private int comment_id;
    private String writeType;
    private String writeName;
}
