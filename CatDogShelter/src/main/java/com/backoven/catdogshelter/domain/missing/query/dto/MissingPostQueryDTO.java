package com.backoven.catdogshelter.domain.missing.query.dto;

import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MissingPostQueryDTO {
    private Long id;
    private String title;
    private String content;
    private String createdAt;
    private int view;
    private String userName;   // 작성자 이름
    private int likeCount;     // 좋아요 수 (인기글에서 사용)
}
