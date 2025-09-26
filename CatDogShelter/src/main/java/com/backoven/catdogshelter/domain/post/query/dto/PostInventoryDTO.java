package com.backoven.catdogshelter.domain.post.query.dto;

/* 자유게시글 목록에 대한 정보를 담아서 전송하는 DTO  */
/* mybatis를 이용해서 조회를 담당하고 entity 없이 DTO만으로도 service, repository 등에도 사용할 수 있어 entity를 사용 안함 */
/* 외래키 user_id와 head_id는 xml 쿼리문에서 조인한 후 결과를 writeName에 저장 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostInventoryDTO {
    private int id;                 // 자유게시글 번호
    private String title;           // 자유게시글 제목
    private String created_at;      // 자유게시글 작성일
    private String updated_at;      // 자유게시글 수정일
    private int view;               // 자유게시글 추천수
    private String writeType;       // 회원 타입 구분
    private String writeName;       // 자유게시글 작성자

}