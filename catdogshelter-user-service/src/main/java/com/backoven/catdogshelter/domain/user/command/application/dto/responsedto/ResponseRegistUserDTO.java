package com.backoven.catdogshelter.domain.user.command.application.dto.responsedto;

import lombok.Data;

@Data
public class ResponseRegistUserDTO {
    private String userName;      // 회원명
    private String userAccount;   // 아이디
    private String email;         // 이메일
    private String detailAddress; // 상세주소 (선택)
    private String answer;        // 비밀번호 찾기 답변
    private String userPhone;     // 전화번호

    private Integer sigunguId;         // 소속 시군구 ID
    private Integer questionCategoryId;// 보안 질문 카테고리 ID
//    private String sidoName;      // 시도 이름
//    private String sigunguName;   // 시군구 이름
//    private String question;      // 보안 질문(카테고리)
}
