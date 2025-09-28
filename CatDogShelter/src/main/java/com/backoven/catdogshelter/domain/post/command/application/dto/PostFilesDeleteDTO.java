package com.backoven.catdogshelter.domain.post.command.application.dto;

/* 자유게시글, 댓글은 논리적 삭제로 Entity에 있는 is_deleted 값을 1로 바꾸는 형식이었다 */
/* 파일은 완전 삭제를 하기 위해서 Json 형태의 입력을 받을 DTO가 필요하다. */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostFilesDeleteDTO {
    private int id;
}
