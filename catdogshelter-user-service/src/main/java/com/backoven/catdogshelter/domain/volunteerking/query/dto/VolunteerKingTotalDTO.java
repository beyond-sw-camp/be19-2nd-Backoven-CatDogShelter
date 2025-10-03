package com.backoven.catdogshelter.domain.volunteerking.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VolunteerKingTotalDTO {
    @Schema(description = "누적 봉사왕 봉사시간(시간)", example = "80")
    private int volunteerTime;

    @Schema(description = "누적 봉사왕 회원 이름", example = "홍길동")
    private String userName;
    @Schema(description = "누적 봉사왕의 등급", example = "댕냥보호천사")
    private String ratingName;
}
