package com.backoven.catdogshelter.domain.shelterhead.command.application.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 회원가입 시 사용자의 입력값을 받아내는 객체용 클래스 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegistShelterheadDTO {
    private String headAccount;
    private String ceoName;                    // 본명
    private String ceoName2;
    private String email;                       // 이메일
    private String pwd;
    private String headPhone;
    private String companyName;                 // 보호소 이름
    private String bizNumber;                  // 사업자 번호
    private String corNumber;                  // 법인등록번호
    private String companyAddress;
    private String openDate;                    // 개업일
    private String closeDate;
    private String sigunguId;
    private String questionCategoryId;
    private String answer;


}
