package com.backoven.catdogshelter.domain.user.command.application.dto.user.fk;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SigunguDTO {
    private Integer sigunguId;   // PK
    private String sigunguCode;  // 시/군/구 코드
    private String sigunguName;  // 시/군/구 이름
    private Integer sidoId;      // FK (시/도 ID)
}
