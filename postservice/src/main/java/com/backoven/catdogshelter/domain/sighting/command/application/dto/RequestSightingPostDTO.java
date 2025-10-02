package com.backoven.catdogshelter.domain.sighting.command.application.dto;

import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.enumeration.AnimalType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
// 입력받은 게시글 DTO
public class RequestSightingPostDTO {
    @Schema(description = "게시글 제목", example = "~에서 검은색 강아지 봤어요")
    private String title;           // 제목
    @Schema(description = "게시글 내용", example = "~에서 중형견 사이즈의 검은색 말티즈를 봤습니다.")
    private String content;         // 내용
    @Schema(description = "목격한 시기", example = "2025.05.25 08:52:00")
    private String sightedAt;       // 목격 시기
    @Schema(description = "목격 장속", example = "동대문 시장")
    private String sightedPlace;    // 목격 장소
    @Schema(description = "목격한 동물의 색상", example = "검은색")
    private String color;           // 색상
    @Schema(description = "동물의 종류", example = "DOG")
    private AnimalType animalType;  // CAT, DOG
    @Schema(description = "동물의 품종", example = "말티즈")
    private String breed;           // 품종
    @Schema(description = "작성자 번호(null 허용, headId와 배타적 관계)")
    private Integer userId;         // 회원 작성자 번호    - headId와 배타적 관계
    @Schema(description = "작성자 번호(null 허용, userId와 배타적 관계)")
    private Integer headId;         // 보호소 작성자 번호  - userId와 배타적 관계
    @Schema(description = "목격한 장소의 시군구 코드", example = "7")
    private Integer sigunguId;      // 시군구 코드
}
