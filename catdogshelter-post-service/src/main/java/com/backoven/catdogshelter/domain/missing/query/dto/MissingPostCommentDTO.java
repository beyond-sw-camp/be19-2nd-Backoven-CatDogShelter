package com.backoven.catdogshelter.domain.missing.query.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MissingPostCommentDTO {
    private Integer id;
    private String content;
    private String createdAt;
    private String userRating;//등급
    private String userName;
}
