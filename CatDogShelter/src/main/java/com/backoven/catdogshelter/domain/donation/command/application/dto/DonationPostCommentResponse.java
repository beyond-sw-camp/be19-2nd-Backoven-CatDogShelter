package com.backoven.catdogshelter.domain.donation.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationPostCommentResponse {
    private Integer id;
    private String content;
    private String writerName;
    private boolean isDeleted;
}
