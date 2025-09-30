package com.backoven.catdogshelter.domain.user.command.application.dto.user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class QuestionCategoryDTO {
    private Integer id;
    private String question;
}