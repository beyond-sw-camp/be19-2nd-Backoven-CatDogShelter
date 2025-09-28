package com.backoven.catdogshelter.domain.donation.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDonationPostRequest {
    private String title;
    private String content;
    private Integer headId;
    private List<String> filePaths; // 파일 업로드 경로
}
