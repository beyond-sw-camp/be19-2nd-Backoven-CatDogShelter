package com.backoven.catdogshelter.domain.adoption.query.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdoptionPostFileDTO {
    private int id;              // 파일 PK
    private String fileRename;   // 리네임된 파일명
    private String filePath;     // 파일 저장 경로
    private String uploadedAt;   // 업로드 시각
    // 이미지 조회 API URL
    private String fileUrl;

}
