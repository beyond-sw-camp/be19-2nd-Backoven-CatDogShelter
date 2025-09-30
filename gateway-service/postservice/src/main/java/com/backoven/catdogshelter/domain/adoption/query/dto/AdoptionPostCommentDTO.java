package com.backoven.catdogshelter.domain.adoption.query.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdoptionPostCommentDTO {
    private int id;              // 댓글 PK
    private String content;      // 댓글 내용
    private String displayDate;   // created_at 또는 updated_at
    private String writerName;    // user_name 또는 company_name (널이 아닌 것만)
}