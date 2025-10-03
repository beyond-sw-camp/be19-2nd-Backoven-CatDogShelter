package com.backoven.catdogshelter.domain.userrating.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ratingDTO {
    @Schema(description = "등급 id")
    private int id;
    @Schema(description = "등급 이름")
    private String name;
    @Schema(description = "등급이 되기 위한 누적 봉사시간 기준값")
    private int standard;
}
