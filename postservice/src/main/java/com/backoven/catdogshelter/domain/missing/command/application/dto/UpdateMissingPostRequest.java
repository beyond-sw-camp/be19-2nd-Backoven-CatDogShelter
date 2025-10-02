package com.backoven.catdogshelter.domain.missing.command.application.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UpdateMissingPostRequest {
    private Integer postId;
    private String title;
    private String content;
    private Integer userId; // 일반회원
    private Integer headId; // 보호소장
    private Integer sigunguId;
    private boolean status;
    private String detailAddress;
    private String gender;
    private Integer age;
    private Integer registrationNum;
    private String breed;
    private String animalType;
    private String color;
    private double weight;
    private String lostDate;
    private String feature;
    private String contact;
    private List<Integer> deleteFileIds; // 삭제할 파일 id 리스트
}
