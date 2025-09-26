package com.backoven.catdogshelter.domain.sighting.command.application.dto;

import com.backoven.catdogshelter.common.util.ReportCategory;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestSightingPostReportDTO {
    private ReportCategory reportCategory;
    private String etcDetail;
    private int postId;
    private Integer userId;
    private Integer headId;
}
