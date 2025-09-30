package com.backoven.catdogshelter.domain.sighting.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddressCodeDTO {
    @Schema(description = "시/도 코드", example = "2")
    private Integer sidoCode;
    @Schema(description = "시/도 이름", example = "서울시")
    private String sidoName;
    @Schema(description = "시/군/구 코드", example = "2")
    private Integer sigunguCode;
    @Schema(description = "시/군/구 이름", example = "동작구")
    private String sigunguName;
}
