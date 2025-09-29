package com.backoven.catdogshelter.domain.missing.query.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MissingPostQueryDetailDTO {
    private Integer id;
    private String title;
    private String content;
    private String createdAt;
    private Integer view;
    private String userRating;       // 등급
    private String userName;   // 작성자 이름
    private Integer likeCount;     // 좋아요 수 (인기글에서 사용)

    private List<MissingPostFileDTO> files;
    private List<MissingPostCommentDTO> comments;

    private boolean status;          // 실종 상태 (처리 여부)
    private String detailAddress;    // 실종 상세 위치
    private String gender;           // 성별 (MALE, FEMALE)
    private Integer age;             // 나이
    private Integer registrationNum; // 동물등록번호
    private String breed;            // 품종
    private String animalType;       // 동물구분타입 (CAT, DOG)
    private String color;            // 색상
    private double weight;       // 체중
    private String lostDate;         // 실종일자
    private String feature;          // 특징
    private String contact;          // 연락처

}
