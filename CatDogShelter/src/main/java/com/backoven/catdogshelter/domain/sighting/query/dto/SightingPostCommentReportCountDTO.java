package com.backoven.catdogshelter.domain.sighting.query.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingPostCommentReportCountDTO {
    private int count;
    private List<SightingPostCommentReportDTO> sightingPostCommentReportDTOList;
}
