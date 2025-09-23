package com.backoven.catdogshelter.domain.sighting.query.dto;

public class SightingSearchDTO {
    private SightingUserDTO writer; // 작성자 검색
    private String title;           // 제목 검색
    private String content;         // 내용 검색
    private String animalType;      // 동물 타입 검색
    private String breed;           // 품종 검색
    private String color;           // 동물 색 검색
    private AddressCodeDTO address; // 시군구 코드를 통한 검색
    private String order;           // 정렬 조건
}
