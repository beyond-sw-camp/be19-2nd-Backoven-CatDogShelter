package com.backoven.catdogshelter.domain.post.command.application.dto;

import com.backoven.catdogshelter.common.enumulation.ReportCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostReportDTO {
    private int id;
    private String etcDetail;
    private ReportCategory category;
    private String createdAt;
    private int postId;
    private Integer userId;
    private Integer headId;
}
