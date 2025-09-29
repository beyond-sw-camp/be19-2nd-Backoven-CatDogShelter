package com.backoven.catdogshelter.domain.missing.query.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MissingPostFileDTO {
    private Integer id;
    private String fileRename;
}
