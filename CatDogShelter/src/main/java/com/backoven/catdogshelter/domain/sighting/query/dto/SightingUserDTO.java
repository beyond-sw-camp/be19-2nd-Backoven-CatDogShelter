package com.backoven.catdogshelter.domain.sighting.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingUserDTO {
    private Integer userId;
    private Integer headId;
    private String name;
}
