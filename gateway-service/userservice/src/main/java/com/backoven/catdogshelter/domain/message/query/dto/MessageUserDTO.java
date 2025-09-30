package com.backoven.catdogshelter.domain.message.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MessageUserDTO {
    private Integer userId;
    private Integer headId;
    private String name;
    private String ratingName;
}
