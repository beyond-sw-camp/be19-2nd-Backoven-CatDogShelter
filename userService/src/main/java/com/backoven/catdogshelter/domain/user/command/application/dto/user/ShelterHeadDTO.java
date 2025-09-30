package com.backoven.catdogshelter.domain.user.command.application.dto.user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ShelterHeadDTO {
    private Integer headId;
    private String ceoName;
    private String ceoName2;
    private String headAccount;
    private String headPassword;
    private String email;
    private String answer;
    private String headPhone;
    private String companyName;
    private String bizNumber;
    private String corNumber;
    private String companyAddress;
    private String openDate;
    private String closeDate;
    private Integer sigunguId;        // FK
    private Integer questionCategoryId; // FK

}
