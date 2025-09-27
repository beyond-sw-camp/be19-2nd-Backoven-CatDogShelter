package com.backoven.catdogshelter.domain.post.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostFilesDTO {
    private int id;
    private String file_rename;
    private String uploaded_at;
}
