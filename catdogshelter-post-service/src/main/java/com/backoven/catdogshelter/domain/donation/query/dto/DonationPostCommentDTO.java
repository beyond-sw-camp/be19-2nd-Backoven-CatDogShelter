package com.backoven.catdogshelter.domain.donation.query.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DonationPostCommentDTO {
    private Integer id;
    private String content;
    private String createdAt;
    private String userRating; //등급
    private String userName;
}
