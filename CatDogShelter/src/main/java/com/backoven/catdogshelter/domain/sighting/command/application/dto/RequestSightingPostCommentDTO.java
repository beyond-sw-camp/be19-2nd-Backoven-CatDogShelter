package com.backoven.catdogshelter.domain.sighting.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestSightingPostCommentDTO {
    @Schema(description = "댓글 내용", example = "저도 거기서 본 것 같아요")
    private String content;
    @Schema(description = "댓글이 달린 게시글 번호", example = "2")
    private int postId;
    @Schema(description = "댓글을 작성 작성자의 유저 번호(null 허용, headId와 배타적 관계)")
    private Integer userId;
    @Schema(description = "댓글을 작성한 작성자의 보호소 번호(null 허용, userId와 배타적 관계)")
    private Integer headId;
}
