package com.backoven.catdogshelter.domain.post.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostFilesDTO {
    private int id;                        // 자유게시글 첨부파일 번호
    private String file_rename;            // 자유게시글 첨부파일 개명
    private String uploaded_at;            // 자유게시글 첨부파일 업로드
}
