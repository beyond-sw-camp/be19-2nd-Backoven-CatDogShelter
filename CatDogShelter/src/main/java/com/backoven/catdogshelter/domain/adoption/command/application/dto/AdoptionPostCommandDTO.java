package com.backoven.catdogshelter.domain.adoption.command.application.dto;

import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.enumeration.AdoptionPostStatus;
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.enumeration.AnimalType;
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.enumeration.Gender;
import com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.enumeration.YnOption;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdoptionPostCommandDTO {
    private Integer id;

    private String title;

    private String content;

    private String createdAt;   // Date → String 변환 (ISO 8601, yyyy-MM-dd 등)

    private String updatedAt;

    private String userPhone;

    private AnimalType animalType; // CAT, DOG

    private String breed;

    private Integer age;

    private String color;

    private Gender gender; // MALE, FEMALE

    private Double weight;

    private AdoptionPostStatus status; // PROTECTING, ADOPTED

    private YnOption vaccination; // Y, N

    private YnOption neutering;   // Y, N

    private Integer view;

    private Boolean isBlind;

    private Boolean isDeleted;

    private Integer userId;

    private Integer headId;

    private Integer sigunguId;

    // 업로드할 파일
    private List<MultipartFile> files;

    private List<AdoptionPostFileDTO> filess;
    private List<AdoptionPostCommentDTO> comments;
    private List<AdoptionPostLikedDTO> likes;
    private List<AdoptionPostReportDTO> reports;
}