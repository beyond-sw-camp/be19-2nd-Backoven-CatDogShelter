package com.backoven.catdogshelter.domain.user.command.application.dto.user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {
    private Integer userId;
    private String userName;
    private String userAccount;
    private String userPassword;
    private String email;
    private String detailAddress;
    private String answer;
    private String userPhone;

    private Integer cumulativeVolunteerTime;
    private Integer monthVolunteerTime;
    private Integer volunteerCount;
    private Integer deactivationTimes;

    private String userStatus;          // ENUM('GENERAL','BLACK','CANCEL')
    private String activationDate;
    private Integer ratingId;           // FK
    private Integer sidoId;
    private Integer sigunguId;          // FK
    private Integer questionCategoryId; // FK

    // 사용자에게 보여줄 내용
    private String sidoName;
    private String sigunguName;
    private String question;
}
