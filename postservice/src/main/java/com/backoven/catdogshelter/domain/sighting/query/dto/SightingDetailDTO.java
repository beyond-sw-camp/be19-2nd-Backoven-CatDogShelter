package com.backoven.catdogshelter.domain.sighting.query.dto;

import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.enumeration.AnimalType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingDetailDTO {
    @Schema(description = "게시글 번호", example = "3")
    private int id;                             // 게시글 번호
    @Schema(description = "게시글 제목", example = "역삼역 앞 강아지")
    private String title;                       // 게시글 제목
    @Schema(description = "게시글 내용", example = "역삼역 앞 조그만 강아지를 봤어요")
    private String content;                     // 게시글 내용
    @Schema(description = "목격 시간", example = "2025.09.30 12:20:00")
    private String sightedAt;                   // 목격 일
    @Schema(description = "목격 장소", example = "역삼역")
    private String sightedPlace;                // 목격 장소
    @Schema(description = "동물 종류(DOT/CAT)", example = "DOG")
    private AnimalType animalType;              // 동물 타입
    @Schema(description = "품종", example = "말티즈")
    private String breed;                       // 동물 품종
    @Schema(description = "작성 시간", example = "2025.10.01 10:08:10")
    private String createdAt;                   // 게시글 작성일
    @Schema(description = "수정 시간", example = "2025.10.01 10:18:10")
    private String updatedAt;                   // 게시글 수정일
    @Schema(description = "조회수", example = "5")
    private int view;                           // 조회수
    @Schema(description = "추천수", example = "5")
    private int likeCount;                      // 추천 수

    @Schema(description = "지역 이름")
    private SightingAddressNameDTO addressName; // 시군구 이름
    @Schema(description = "작성자")
    private SightingUserDTO writer;             // 작성자
    @Schema(description = "파일 리스트")
    private List<SightingFileDTO> files;        // 파일
    @Schema(description = "댓글 리스트")
    private List<SightingCommentDTO> comments;  // 댓글

}