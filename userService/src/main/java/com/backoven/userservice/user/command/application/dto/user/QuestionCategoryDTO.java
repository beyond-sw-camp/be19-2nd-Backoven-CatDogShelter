package com.backoven.userservice.user.command.application.dto.user;

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