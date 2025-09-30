package com.backoven.catdogshelter.domain.notice.command.application.dto;

import lombok.Data;

@Data
public class LikeRequest {      //좋아요 토글 요청
    private Integer userId;  // 일반 유저가 누를 때
    private Integer headId;  // 보호소장이 누를 때
    // 둘 중 하나만 넣어야 함
}
