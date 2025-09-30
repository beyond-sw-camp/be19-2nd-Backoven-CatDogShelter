package com.backoven.catdogshelter.domain.sighting.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingSearchDTO {
    @Schema(description = "게시글 작성자", example = "홍길동")
    private String writer;          // 작성자 검색
    @Schema(description = "제목", example = "대방")
    private String title;           // 제목 검색
    @Schema(description = "내용", example = "대방")
    private String content;         // 내용 검색
    @Schema(description = "동물 종류(DOT/CAT)", example = "CAT")
    private String animalType;      // 동물 타입 검색
    @Schema(description = "품종", example = "말티즈")
    private String breed;           // 품종 검색
    @Schema(description = "색상", example = "갈색")
    private String color;           // 동물 색 검색
    @Schema(description = "시군구 코드", example = "5")
    private Integer sigunguCode;    // 시군구 코드를 통한 검색 (int이면 쿼리스트링에 값이 없는 경우 0으로 들어가므로 Integer로 선언)
    @Schema(description = "정렬 순서" +
            "\n0: 최신순" +
            "\n1: 최신역순" +
            "\n2: 조회순" +
            "\n3: 조회역순" +
            "\n4: 추천순" +
            "\n5: 추천역순", example = "3")
    private Integer order;          // 정렬 조건
                                        // 0: 최신순
                                        // 1: 최신 역순
                                        // 2: 조회수 내림차순
                                        // 3: 조회수 오름차순
                                        // 4: 추천수 내림차순
                                        // 5: 추천수 오름차순


}
