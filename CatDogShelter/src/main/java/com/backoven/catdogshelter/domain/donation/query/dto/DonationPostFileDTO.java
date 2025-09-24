package com.backoven.catdogshelter.domain.donation.query.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DonationPostFileDTO {
    private int id;
    private String fileRename;
}
