package com.backoven.catdogshelter.domain.user.command.application.dto.user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoginHistoryDTO {
    private Integer id;
    private String ipAddress;
    private String loggedAt;
    private Integer userId;   // FK
    private Integer headId;   // FK

}
