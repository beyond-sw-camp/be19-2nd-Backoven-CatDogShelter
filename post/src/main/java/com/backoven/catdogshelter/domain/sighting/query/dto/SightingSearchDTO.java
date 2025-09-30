package com.backoven.catdogshelter.domain.sighting.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingSearchDTO {
    private String writer;          // 작성자 검색
    private String title;           // 제목 검색
    private String content;         // 내용 검색
    private String animalType;      // 동물 타입 검색
    private String breed;           // 품종 검색
    private String color;           // 동물 색 검색
    private Integer sigunguCode;    // 시군구 코드를 통한 검색 (int이면 쿼리스트링에 값이 없는 경우 0으로 들어가므로 Integer로 선언)
    private Integer order;          // 정렬 조건
                                        // 0: 최신순
                                        // 1: 최신 역순
                                        // 2: 조회수 내림차순
                                        // 3: 조회수 오름차순
                                        // 4: 추천수 내림차순
                                        // 5: 추천수 오름차순


}
