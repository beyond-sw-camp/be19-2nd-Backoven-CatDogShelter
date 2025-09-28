package com.backoven.catdogshelter.domain.sighting.command.application.dto;

import com.backoven.catdogshelter.common.util.ReportCategory;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestSightingPostCommentReportDTO {
    private ReportCategory reportCategory;
    private String etcDetail;
    private int commentId;
    private Integer userId;
    private Integer headId;
}
