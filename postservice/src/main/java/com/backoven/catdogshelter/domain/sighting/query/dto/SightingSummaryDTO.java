package com.backoven.catdogshelter.domain.sighting.query.dto;

/* 설명. 게시글 목록 DTO */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingSummaryDTO {
    @Schema(description = "게시글 ID", example = "5")
    private int id;
    @Schema(description = "게시글 제목", example = "고양이를 목격했어요")
    private String title;
    @Schema(description = "작성 시간", example = "2025.10.01 10:08:10")
    private String createdAt;
    @Schema(description = "수정 시간", example = "2025.10.01 12:08:10")
    private String updatedAt;

    @Schema(description = "조회수", example = "5")
    private int view;
    @Schema(description = "추천수", example = "5")
    private int likeCount;
    @Schema(description = "작성자")
    private SightingUserDTO writer;
}
