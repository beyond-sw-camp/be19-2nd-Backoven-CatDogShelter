package com.backoven.catdogshelter.domain.sighting.query.dto;

/* 설명. 게시글 목록 DTO */

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingSummaryDTO {
    private int id;
    private String title;
    private String createdAt;
    private String updatedAt;
    private int view;
    private int likeCount;

    private SightingUserDTO writer;
}
