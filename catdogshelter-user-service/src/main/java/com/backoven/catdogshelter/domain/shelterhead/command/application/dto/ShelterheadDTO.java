package com.backoven.catdogshelter.domain.shelterhead.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShelterheadDTO {
    private String ceoName;                    // 본명
    private String ceoName2;
    private String email;                       // 이메일
    private String pwd;                         // 암호화를 하기 위해서 entity에서 선언한 부분을 Shelter_headServiceImpl에서 수정
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

    private String headAccount;
}
