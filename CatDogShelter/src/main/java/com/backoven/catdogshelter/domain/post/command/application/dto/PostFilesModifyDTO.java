package com.backoven.catdogshelter.domain.post.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostFilesModifyDTO {
    private int id;
    private String fileRename;
    private String uploadedAt;
    private String filePath;
}
