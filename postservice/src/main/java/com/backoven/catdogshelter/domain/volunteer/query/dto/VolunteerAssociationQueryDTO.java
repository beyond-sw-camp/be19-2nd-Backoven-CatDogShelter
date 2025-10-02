package com.backoven.catdogshelter.domain.volunteer.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Schema(name = "봉사모임 게시글DTO")
public class VolunteerAssociationQueryDTO {
    @Schema(name = "게시글 번호")
    private Integer id;
    @Schema(name = "게시글 제목")
    private String title;
    @Schema(name = "게시글 내용")
    private String content;
    @Schema(name = "게시글 작성일")
    private String createdAt;
    @Schema(name = "봉사모임 활동일")
    private String startDate;
    @Schema(name = "봉사 시간")
    private Integer time;
    @Schema(name = "상세 주소")
    private String detailAddress;
    @Schema(name = "모집인원")
    private Integer numberOfPeople;
    @Schema(name = "모집마감여부")
    private Boolean deadline;
    @Schema(name = "봉사종료여부")
    private Boolean isEnd;
    @Schema(name = "보호소 번호")
    private Integer headId;
    @Schema(name = "보호소장 이름")
    private String ceoName;
    @Schema(name = "보호소 이름")
    private String companyName;

    // ✅ 추가
    private Integer sigunguId;
    private String sigunguName;
}
