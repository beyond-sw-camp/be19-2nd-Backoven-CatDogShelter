package com.backoven.catdogshelter.domain.sighting.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingUserDTO {
    @Schema(description = "작성자 번호(유저 번호)(null 허용, headId와 배타적)")
    private Integer userId;
    @Schema(description = "작성자 번호(보호소 번호)(null 허용, userId와 배타적)")
    private Integer headId;
    @Schema(description = "작성자 이름", example = "홍길동")
    private String name;
    @Schema(description = "작성자 등금", example = "발바닥")
    private String ratingName;
}
