package com.backoven.catdogshelter.domain.shelterhead.command.application.dto.responsedto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRegistShelterheadDTO {

    private String ceoName;                    // 본명
    private String ceoName2;
    private String email;                       // 이메일
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

    private String headAccount;    // 회원 가입 이후 생성된 회원 교유 번호
}
