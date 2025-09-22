package com.backoven.catdogshelter.domain.post.query.dto;
/* 자유게시글 외래키로 지정된 댓글 조회할 때 사용되는 DTO */

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostCommentDTO {
    private int id;
    private String content;
    private String created_at;
    private int post_id;            // 자유게시글(post)

    // user_id와 head_id 베타적 관계여서 null이 들어가므로 래퍼 클래스 사용
    private Integer user_id;        // 일반 회원
    private Integer head_id;        // 보호소장 회원

}
