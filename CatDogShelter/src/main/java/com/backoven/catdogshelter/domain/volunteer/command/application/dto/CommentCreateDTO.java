package com.backoven.catdogshelter.domain.volunteer.command.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentCreateDTO {
    private Integer postId;
    private String content;
    private LikeToggleRequest.ActorType actorType;
    private Integer userId;
    private Integer headId;
}
