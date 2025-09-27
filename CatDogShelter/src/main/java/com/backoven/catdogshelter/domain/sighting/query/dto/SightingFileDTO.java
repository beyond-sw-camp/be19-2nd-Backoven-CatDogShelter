package com.backoven.catdogshelter.domain.sighting.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingFileDTO {
    private String fileRename;
    private String filePath;
}
