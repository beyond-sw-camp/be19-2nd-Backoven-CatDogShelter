package com.backoven.catdogshelter.domain.message.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestMessageSummaryDTO {
    @Schema(description = "유저 번호(null 허용, headId와 배타적 관계)")
    private Integer userId = null;
    @Schema(description = "보호소 번호(null 허용, userId와 배타적 관계)")
    private Integer headId = null;
}
