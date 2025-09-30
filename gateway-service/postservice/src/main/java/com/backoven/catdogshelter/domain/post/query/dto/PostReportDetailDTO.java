package com.backoven.catdogshelter.domain.post.query.dto;

/* 신고 내역을 조회하기 위해 필요한 DTO */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostReportDetailDTO {
    private int reportCount;
    private int id;
    private String category;
    private String etc_detail;
    private String created_at;
    private int post_id;
    private String writeType;
    private String writeName;
}
