package com.backoven.catdogshelter.domain.sighting.query.dto;

import lombok.*;
import org.springframework.core.io.Resource;

import java.nio.file.Path;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingFileDTO {
    private String fileRename;
    private String filePath;
}
