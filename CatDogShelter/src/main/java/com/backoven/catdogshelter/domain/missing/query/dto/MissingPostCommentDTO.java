package com.backoven.catdogshelter.domain.missing.query.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MissingPostCommentDTO {
    private int id;
    private String content;
    private String createdAt;
    private String userName;
}
