package com.backoven.catdogshelter.domain.adoption.command.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdoptionPostCommentDTO {
    private Integer id;
    private String content;
    private String createdAt;
    private String updatedAt;
    private Boolean isBlinded;
    private Boolean isDeleted;

    private Integer userId;
    private Integer headId;
}