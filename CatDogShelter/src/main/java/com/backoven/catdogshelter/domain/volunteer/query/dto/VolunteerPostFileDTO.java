// src/main/java/com/backoven/catdogshelter/domain/volunteer/query/dto/VolunteerPostFileDTO.java
package com.backoven.catdogshelter.domain.volunteer.query.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VolunteerPostFileDTO {
    private Integer id;
    private String fileRename;
    private String filePath;
    private String uploadedAt;
}
