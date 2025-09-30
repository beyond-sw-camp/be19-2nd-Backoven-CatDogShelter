package com.backoven.catdogshelter.domain.missing.command.application.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MissingPostCommentResponse {
    private Integer id;
    private String content;
    private String writerName;
    private boolean isDeleted;
}
