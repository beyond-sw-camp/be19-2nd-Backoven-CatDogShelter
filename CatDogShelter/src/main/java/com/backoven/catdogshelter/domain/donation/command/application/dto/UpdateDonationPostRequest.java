package com.backoven.catdogshelter.domain.donation.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDonationPostRequest {
    private Long postId;
    private String title;
    private String content;
    private Long headId;
    private String headName; // ceoName
}
