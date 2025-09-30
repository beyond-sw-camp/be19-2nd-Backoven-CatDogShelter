package com.backoven.catdogshelter.domain.message.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestMessageSummaryDTO {
    @Schema(description = "유저 번호", example = "2")
    private Integer userId = null;
    @Schema(description = "보호소 번호", example = "5")
    private Integer headId = null;
}
