package com.backoven.catdogshelter.domain.sighting.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingPostReportCountDTO {
    @Schema(description = "해당 게시글 신고 개수", example = "5")
    private int count;
    @Schema(description = "게시글이 신고당한 내역")
    private List<SightingPostReportDTO> sightingPostReportDTOList;
}
