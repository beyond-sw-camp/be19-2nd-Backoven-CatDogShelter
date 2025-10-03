package com.backoven.catdogshelter.domain.message.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MessageUserDTO {
    @Schema(description = "유저 번호(null 허용, headId와 배타적 관계)")
    private Integer userId;
    @Schema(description = "보호소 번호(null 허용, userId와 배타적 관계)")
    private Integer headId;
    @Schema(description = "유저 번호 or 보호소 번호에 매칭되는 이름", example = "홍길동")
    private String name;
    @Schema(description = "유저의 등급", example = "발바닥")
    private String ratingName;
}
