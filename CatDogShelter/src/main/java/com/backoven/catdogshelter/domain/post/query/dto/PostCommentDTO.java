package com.backoven.catdogshelter.domain.post.query.dto;

/* 조회만 할때는 id(key)가 필요없다고 생각한다. 하지만 추후 수정, 삭제 등이 일어날 경우를 대비해 key 값은 계속해서 추가하는 것이 좋다.  */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostCommentDTO {
    private int id;                 // 자유게시글 댓글 번호
    private String content;         // 자유게시글 댓글 내용
    private String created_at;      // 자유게시글 생성 일자
    private String updated_at;      // 자유게시글 수정 일자
    private int post_id;            // 자유게시글 번호
}
