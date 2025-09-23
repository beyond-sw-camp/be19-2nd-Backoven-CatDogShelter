package com.backoven.catdogshelter.domain.sighting.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddressCodeDTO {
    private Integer sidoCode;
    private String sidoName;
    private Integer sigunguCode;
    private String sigunguName;
}
