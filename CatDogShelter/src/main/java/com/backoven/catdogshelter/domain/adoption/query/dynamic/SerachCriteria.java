package com.backoven.catdogshelter.domain.adoption.query.dynamic;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SerachCriteria {
    // 키워드 검색용
    private String title;      // 제목 검색 키워드
    private String content;    // 내용 검색 키워드
    private String breed;      // 품종 검색 키워드

    // 동물 조건 검색용
    private String animalType;   // "강아지" or "고양이" → 쿼리에서 DOG/CAT으로 변환
    private String gender;       // "MALE" / "FEMALE"
    private String vaccination;  // "Y" / "N"
    private String neutering;    // "Y" / "N"
}
