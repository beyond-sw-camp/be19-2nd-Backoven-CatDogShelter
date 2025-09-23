package com.backoven.catdogshelter.domain.sighting.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingCommentDTO {
    private int id;
    private String content;
    private String createdAt;
    private String updatedAt;

    private SightingUserDTO writer;      // 작성자
}
