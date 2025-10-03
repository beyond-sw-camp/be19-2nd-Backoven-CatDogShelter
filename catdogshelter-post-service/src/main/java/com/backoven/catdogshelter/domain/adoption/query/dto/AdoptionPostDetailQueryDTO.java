package com.backoven.catdogshelter.domain.adoption.query.dto;


import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdoptionPostDetailQueryDTO {
    private int id;
    private String title;
    private String content;
    private String userPhone;
    private String writerName;
    private String userRating;   // 회원 등급
    private int view;
    private int recommendCount;

    private String animalType;
    private String breed;
    private int age;
    private String color;
    private String gender;
    private double weight;
    private String status;
    private String vaccination;
    private String neutering;

    // 파일 리스트
    private List<AdoptionPostFileDTO> files;

    // 댓글 리스트
    private List<AdoptionPostCommentDTO> comments;
}