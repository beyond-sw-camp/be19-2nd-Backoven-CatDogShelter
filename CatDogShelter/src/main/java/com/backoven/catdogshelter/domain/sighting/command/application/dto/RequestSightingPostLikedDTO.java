package com.backoven.catdogshelter.domain.sighting.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestSightingPostLikedDTO {
    @Schema(description = "추천할 게시글 번호", example = "2")
    private int postId;
    @Schema(description = "추천을 한 유저 번호(null 허용, headId와 배타적 관계)")
    private Integer userId;
    @Schema(description = "추천을 한 보호소 번호(null 허용, userId와 배타적 관계)")
    private Integer headId;
}
