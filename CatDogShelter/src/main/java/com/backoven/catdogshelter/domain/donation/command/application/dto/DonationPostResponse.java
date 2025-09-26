package com.backoven.catdogshelter.domain.donation.command.application.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DonationPostResponse {
    private Long id;
    private String title;
    private String content;
    private int view;
    private String headName;
    private List<String> filePaths;
}
