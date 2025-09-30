package com.backoven.catdogshelter.domain.sighting.query.dto;

import com.backoven.catdogshelter.common.util.ReportCategory;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingPostReportDTO {
    private int id;
    private ReportCategory reportCategory;
    private String etcDetail;
    private String createdAt;
    private boolean status;
    private int postId;
    private SightingUserDTO writer;
}
