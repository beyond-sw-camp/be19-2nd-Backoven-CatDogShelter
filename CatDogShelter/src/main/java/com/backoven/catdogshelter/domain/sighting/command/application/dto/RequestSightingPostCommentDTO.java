package com.backoven.catdogshelter.domain.sighting.command.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestSightingPostCommentDTO {
    private String content;
    private int postId;
    private Integer userId;
    private Integer headId;
}
