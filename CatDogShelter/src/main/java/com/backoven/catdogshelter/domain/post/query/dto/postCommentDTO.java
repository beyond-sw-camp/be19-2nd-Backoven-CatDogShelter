package com.backoven.catdogshelter.domain.post.query.dto;
/* 해당 자유게시글 외래키로 지정된 댓글 조회 */

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class postCommentDTO {
    private int id;
    private String created_at;
    private int post_id;

    private Integer user_id;
    private Integer head_id;

}
