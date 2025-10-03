package com.backoven.catdogshelter.domain.missing.command.application.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateMissingPostRequest {
    private String title;
    private String content;
    private Integer userId;   // 일반회원 ID (선택)
    private Integer headId;   // 보호소장 ID (선택)
    private Integer sigunguId;

    private List<String> filePaths;

    private boolean status;          // 실종 상태
    private String detailAddress;    // 상세 위치
    private String gender;           // 성별
    private Integer age;             // 나이
    private Integer registrationNum; // 등록번호
    private String breed;            // 품종
    private String animalType;       // 동물구분
    private String color;            // 색상
    private double weight;           // 체중
    private String lostDate;         // 실종일자
    private String feature;          // 특징
    private String contact;          // 연락처
}
