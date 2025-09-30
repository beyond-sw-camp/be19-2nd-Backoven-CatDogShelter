package com.backoven.catdogshelter.domain.sighting.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingCommentDTO {
    @Schema(description = "댓글 번호", example = "3")
    private int id;
    @Schema(description = "댓글 내용", example = "저도 거기서 봤어요")
    private String content;
    @Schema(description = "작성일", example = "2025.06.26 02:30:08")
    private String createdAt;
    @Schema(description = "수정일", example = "2025.06.26 03:30:08")
    private String updatedAt;
    @Schema(description = "작성자")
    private SightingUserDTO writer;      // 작성자
}
