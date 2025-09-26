package com.backoven.catdogshelter.domain.post.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostLikedDTO {
    private int id;
    private int postId;
    private Integer userId;
    private Integer headId;
}
