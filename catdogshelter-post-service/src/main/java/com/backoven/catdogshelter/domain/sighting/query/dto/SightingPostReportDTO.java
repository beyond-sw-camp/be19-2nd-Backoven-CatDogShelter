package com.backoven.catdogshelter.domain.sighting.query.dto;

import com.backoven.catdogshelter.common.enumulation.ReportCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingPostReportDTO {
    @Schema(description = "댓글 신고 id", example = "5")
    private int id;
    @Schema(description = "신고 카테고리", example = "SPAM")
    private ReportCategory reportCategory;
    @Schema(description = "기타 신고 사유(카테고리가 기타인 경우만 작성)", example = "댓글 도배")
    private String etcDetail;
    @Schema(description = "신고일", example = "2025.09.30 12:30:43")
    private String createdAt;
    @Schema(description = "관리자가 확인했는지 나타내는 값", example = "false")
    private boolean status;
    @Schema(description = "신고당한 게스글 아이디", example = "5")
    private int postId;
    @Schema(description = "신고자")
    private SightingUserDTO writer;
}