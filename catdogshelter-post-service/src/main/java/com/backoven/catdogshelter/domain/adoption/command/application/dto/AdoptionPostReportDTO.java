package com.backoven.catdogshelter.domain.adoption.command.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdoptionPostReportDTO {
    private Integer id;
    private String category;
    private String etcDetail;
    private String createdAt;
    private Boolean status;

    private Integer adoptionPostId;
    private Integer userId;
    private Integer headId;
}