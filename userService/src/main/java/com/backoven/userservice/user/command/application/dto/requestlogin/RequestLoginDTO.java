package com.backoven.userservice.user.command.application.dto.requestlogin;

import lombok.Data;

@Data
public class RequestLoginDTO {
    private String userAccount;
    private String userPassword;
}
