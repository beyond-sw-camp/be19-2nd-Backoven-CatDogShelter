package com.backoven.catdogshelter.domain.user.command.application.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// 마이페이지 정보 수정
public class ResponseModifyUserDTO {
    private String detailAddress;           // 상세주소

    private Integer sigunguId;              // 소속 시군구 FK
    private String sigunguName;             // 소속 시도 FK

    private Integer questionCategoryId;     // 지정질문카테고리번호 FK
    private String answer;                  // 지정답변
}
