package com.backoven.catdogshelter.domain.post.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostCommentRegistDTO {
    private int id;
    private String content;
    private String createdAt;
    private String postId;
    private Integer userId;
    private Integer headId;
}
