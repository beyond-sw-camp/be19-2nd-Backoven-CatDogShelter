package com.backoven.catdogshelter.domain.shelter_head.command.application.dto;

import lombok.Data;

@Data
public class RequestLoginDTO {
    private String headAccount;
    private String pwd;

}
