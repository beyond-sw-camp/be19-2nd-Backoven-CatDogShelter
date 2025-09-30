package com.backoven.catdogshelter.domain.volunteerking.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VolunteerKingMonthDTO {
    @Schema(description = "수상 년도", example = "2025")
    private int year;
    @Schema(description = "수상 월", example = "3")
    private int month;
    @Schema(description = "월별 봉사왕 봉사시간(시간)", example = "80")
    private int volunteerTime;
    @Schema(description = "누적 봉사왕 회원 이름", example = "홍길동")
    private String userName;
    @Schema(description = "누적 봉사왕의 등급", example = "댕냥보호천사")
    private String ratingName;
}
