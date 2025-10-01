package com.backoven.catdogshelter.domain.volunteer.command.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VolunteerPostCommentCreateDTO {
    private Integer postId;
    private String content;
    private VolunteerPostLikeToggleRequest.ActorType actorType;
    private Integer userId;
    private Integer headId;
}
