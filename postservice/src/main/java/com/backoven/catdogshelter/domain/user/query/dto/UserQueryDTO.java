package com.backoven.catdogshelter.domain.user.query.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserQueryDTO {

    private Integer userId;              // PK
    private String userName;             // 회원명
    private String userAccount;          // 아이디
    private String userPassword;         // 비밀번호
    private String email;                // 이메일
    private String detailAddress;        // 상세주소
    private String answer;               // 지정답변
    private String userPhone;            // 전화번호
    private Integer cumulativeVolunteerTime;  // 누적봉사시간
    private Integer monthVolunteerTime;       // 월봉사시간
    private Integer volunteerCount;           // 봉사횟수
    private Integer deactivationTimes;        // 블라인드 횟수
    private String userStatus;           // 회원상태 (GENERAL, BLACK, CANCEL)
    private String activationDate;       // 정지 해제일
    private Integer ratingId;            // 회원등급 FK
    private Integer sigunguId;           // 소속 시군구 FK
    private Integer questionCategoryId;  // 지정질문카테고리번호 FK
}
