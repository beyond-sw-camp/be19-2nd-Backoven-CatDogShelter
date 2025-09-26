package com.backoven.catdogshelter.domain.post.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostLikedDescDTO {
    private int id;                 // 자유게시글 번호
    private String title;           // 자유게시글 제목
    private String created_at;      // 자유게시글 작성일
    private String updated_at;      // 자유게시글 수정일
    private String writeType;       // 회원 타입 구분
    private String writeName;       // 자유게시글 작성자
    private int likeCount;          // postLiked 테이블과 조인해서 좋아요 수 보여주기
}
