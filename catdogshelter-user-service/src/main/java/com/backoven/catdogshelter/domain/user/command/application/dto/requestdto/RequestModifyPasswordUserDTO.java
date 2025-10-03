package com.backoven.catdogshelter.domain.user.command.application.dto.requestdto;

import lombok.Data;

@Data
public class RequestModifyPasswordUserDTO {
    private String currentPwd;
    private String newPwd;

    private Integer questionId;
    private String answer;

}
