package com.backoven.catdogshelter.domain.user.command.application.dto.requestdto;

import lombok.Data;

// 설명. 회원가입 시 사용자의 입력값을 받아낼 커맨드 객체용 클래스
@Data
public class RequestRegistUserDTO {
    private String userName;           // 회원명
    private String userAccount;        // 아이디
    private String userPassword;       // 평문 비밀번호 (Service에서 암호화)

    private String email;              // 이메일
    private String detailAddress;      // 상세주소 (선택)
    private String answer;             // 비밀번호 찾기 답변
    private String userPhone;          // 전화번호

    private Integer sigunguId;         // 소속 시군구 ID
    private Integer questionCategoryId;// 보안 질문 카테고리 ID
}
