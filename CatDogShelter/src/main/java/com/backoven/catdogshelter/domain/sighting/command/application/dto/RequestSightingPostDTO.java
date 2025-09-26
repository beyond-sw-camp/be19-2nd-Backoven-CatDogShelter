package com.backoven.catdogshelter.domain.sighting.command.application.dto;

import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.enumeration.AnimalType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
// 입력받은 게시글 DTO
public class RequestSightingPostDTO {
    private String title;           // 제목
    private String content;         // 내용
    private String sightedAt;       // 목격 시기
    private String sightedPlace;    // 목격 장소
    private String color;           // 색상
    private AnimalType animalType;  // CAT, DOG
    private String breed;           // 품종
    private Integer userId;         // 회원 작성자 번호    - headId와 배타적 관계
    private Integer headId;         // 보호소 작성자 번호  - userId와 배타적 관계
    private Integer sigunguId;      // 시군구 코드


}
