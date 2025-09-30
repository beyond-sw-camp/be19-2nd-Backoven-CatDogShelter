package com.backoven.catdogshelter.domain.shelterhead.command.application.dto;

import lombok.Data;

@Data
public class RequestLoginDTO {
    private String headAccount;
    private String pwd;

}
