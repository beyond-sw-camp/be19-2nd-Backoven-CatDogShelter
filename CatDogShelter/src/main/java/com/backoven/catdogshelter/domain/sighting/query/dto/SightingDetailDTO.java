package com.backoven.catdogshelter.domain.sighting.query.dto;

import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.enumeration.AnimalType;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingDetailDTO {
    private int Id;                             // 게시글 번호
    private String title;                       // 게시글 제목
    private String content;                     // 게시글 내용
    private String sightedAt;                   // 목격 일
    private String sightedPlace;                // 목격 장소
    private AnimalType animalType;              // 동물 타입
    private String breed;                       // 동물 품종

    private String createdAt;                   // 게시글 작성일
    private String updatedAt;                   // 게시글 수정일
    private int view;                           // 조회수
    private int likeCount;                           // 좋아요 수

    private SightingUserDTO writer;              // 작성자

    private AddressCodeDTO address;             // 시군구 코드

    private List<SightingFileDTO> files;        // 파일
    private List<SightingCommentDTO> comments;  // 댓글

}