package com.backoven.catdogshelter.domain.notice.command.application.dto;

import lombok.Data;

@Data
public class LikeRequest {
    private Long userId; // 둘 중 하나만 채우기
    private Long headId;
}
