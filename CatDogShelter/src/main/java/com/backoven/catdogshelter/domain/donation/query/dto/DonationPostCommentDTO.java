package com.backoven.catdogshelter.domain.donation.query.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DonationPostCommentDTO {
    private int id;
    private String content;
    private String createdAt;
    private String userName;
}
