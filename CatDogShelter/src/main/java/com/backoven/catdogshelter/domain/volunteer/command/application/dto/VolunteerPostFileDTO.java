package com.backoven.catdogshelter.domain.volunteer.command.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class VolunteerPostFileDTO {
    private Integer id;          // 삭제용으로도 사용
    private String fileRename;
    private String filePath;
    private String uploadedAt;
}
