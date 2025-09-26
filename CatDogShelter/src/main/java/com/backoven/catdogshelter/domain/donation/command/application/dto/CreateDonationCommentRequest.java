package com.backoven.catdogshelter.domain.donation.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDonationCommentRequest {
    private Long postId;
    private String content;
    private Long userId;
}
