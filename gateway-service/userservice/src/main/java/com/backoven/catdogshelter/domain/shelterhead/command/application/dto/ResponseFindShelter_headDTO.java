package com.backoven.catdogshelter.domain.shelterhead.command.application.dto;

import lombok.Data;

@Data
public class ResponseFindShelter_headDTO {
    private String ceoName;
    private String ceoName2;
    private String email;
    private String headPhone;
    private String companyName;
    private String bizNumber;
    private String corNumber;
    private String companyAddress;
    private String openDate;
    private String closeDate;
    private String sigunguId;
    private String questionCategoryId;
    private String answer;

    private String headAccount;
}
