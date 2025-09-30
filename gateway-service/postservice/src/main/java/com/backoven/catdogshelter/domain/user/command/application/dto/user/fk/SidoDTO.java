package com.backoven.catdogshelter.domain.user.command.application.dto.user.fk;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SidoDTO {
    private Integer sidoId;      // PK
    private String sidoCode;     // 시/도 코드
    private String sidoName;     // 시/도 이름
}