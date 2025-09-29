package com.backoven.userservice.user.command.application.dto.requestlogin;

import lombok.Data;

@Data
public class RequestModifyPasswordUserDTO {
    private String currentPwd;
    private String newPwd;

    private Integer questionId;
    private String answer;

}
