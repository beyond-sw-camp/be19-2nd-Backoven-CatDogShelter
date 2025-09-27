package com.backoven.catdogshelter.domain.donation.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDonationPostRequest {
    private Long postId;
    private String title;
    private String content;
    private Long headId;
    private String headName; // ceoName
    private List<Long> deleteFileIds; // 삭제할 파일 id 리스트리스트
}
