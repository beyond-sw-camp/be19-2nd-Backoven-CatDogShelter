package com.backoven.catdogshelter.domain.post.query.dto;
/* 회원 혹은 보호소장이 작성한 자유게시글을 조회하기 위해 필요한 DTO */

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class postDTO {
    private int id;
    private String title;
    private String content;
    private String created_at;
    private String view;

    // user_id와 head_id 베타적 관계여서 null이 들어가므로 래퍼 클래스 사용
    private Integer user_id;
    private Integer head_id;
}
