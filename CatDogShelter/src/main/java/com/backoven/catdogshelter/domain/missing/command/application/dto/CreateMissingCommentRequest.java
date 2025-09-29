package com.backoven.catdogshelter.domain.missing.command.application.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateMissingCommentRequest {
    private Integer postId;
    private String content;
    private Integer userId;
}
