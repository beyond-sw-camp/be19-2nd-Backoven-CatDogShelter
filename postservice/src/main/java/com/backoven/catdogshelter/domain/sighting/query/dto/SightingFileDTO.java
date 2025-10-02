package com.backoven.catdogshelter.domain.sighting.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingFileDTO {
    @Schema(description = "fileReName", example = "ecdfe3810d534d8fb40578fa1bf32c59.png")
    private String fileRename;
    @Schema(description = "filePath", example = "sighting")
    private String filePath;
}
