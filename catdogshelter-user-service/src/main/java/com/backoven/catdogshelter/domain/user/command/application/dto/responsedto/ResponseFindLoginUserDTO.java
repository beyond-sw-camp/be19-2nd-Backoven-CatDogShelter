package com.backoven.catdogshelter.domain.user.command.application.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// 마이페이지에 뿌려지는 내용
public class ResponseFindLoginUserDTO {
    private Integer userId;                 // PK
    private String userName;                // 회원명
    private String userAccount;             // 아이디
    private String email;                   // 이메일
    private String detailAddress;           // 상세주소
    private String answer;                  // 지정답변
    private String userPhone;               // 전화번호

    // 회원정보 (default)
    private Integer cumulativeVolunteerTime;// 누적봉사시간
    private Integer monthVolunteerTime;     // 월봉사시간
    private Integer volunteerCount;         // 봉사횟수

    // FK
    private Integer ratingId;               // 회원등급 FK
    private Integer sigunguId;              // 소속 시군구 FK
    private Integer questionCategoryId;     // 지정질문카테고리번호 FK
}
