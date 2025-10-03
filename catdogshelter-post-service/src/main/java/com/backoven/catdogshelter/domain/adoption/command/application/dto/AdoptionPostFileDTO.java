package com.backoven.catdogshelter.domain.adoption.command.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdoptionPostFileDTO {
    private Integer id;
    private String fileRename;
    private String filePath;
    private String uploadedAt;

    private String fileUrl;   // ← 추가
}