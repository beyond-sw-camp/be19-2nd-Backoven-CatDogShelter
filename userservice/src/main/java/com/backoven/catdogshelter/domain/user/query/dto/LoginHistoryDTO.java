package com.backoven.catdogshelter.domain.user.query.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginHistoryDTO {
    private Integer id;          // PK
    private String ipAddress;    // 로그인 IP
    private LocalDateTime loggedAt; // 로그인 시간
    private Integer userId;      // 일반회원 FK
    private Integer headId;      // 보호소장 FK
}