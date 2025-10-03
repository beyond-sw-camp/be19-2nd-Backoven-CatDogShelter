package com.backoven.catdogshelter.domain.user.command.application.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestModifyUserDTO {
    private String detailAddress;           // 상세주소

    private Integer sigunguId;              // 소속 시군구 FK

    private Integer questionCategoryId;     // 지정질문카테고리번호 FK
    private String answer;                  // 지정답변
}
