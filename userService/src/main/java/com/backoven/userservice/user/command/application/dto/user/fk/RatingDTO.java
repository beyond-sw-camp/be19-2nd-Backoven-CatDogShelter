package com.backoven.userservice.user.command.application.dto.user.fk;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RatingDTO {
    private Integer id;
    private String name;
    private Integer standard;
}
