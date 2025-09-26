package com.backoven.catdogshelter.domain.adoption.query.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdoptionPostDTO {
    private int id;

    private String title;

    private String content;

    private String createdAt;

    private String updatedAt;

    private String userPhone;

    private String animalType; // CAT, DOG

    private String breed;

    private int age;

    private String color;

    private String gender; // MALE, FEMALE

    private double weight;

    private String status; // PROTECTING, ADOPTED

    private String vaccination; // Y, N

    private String neutering;   // Y, N

    private int view;

    private boolean isBlind;

    private boolean isDeleted;

    private Integer userId;

    private Integer headId;

    private int sigunguId;
}