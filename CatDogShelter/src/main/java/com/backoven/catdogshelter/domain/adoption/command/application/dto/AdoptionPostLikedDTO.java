package com.backoven.catdogshelter.domain.adoption.command.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdoptionPostLikedDTO {
//    private Integer id;
    private Integer userId;
    private Integer headId;
}