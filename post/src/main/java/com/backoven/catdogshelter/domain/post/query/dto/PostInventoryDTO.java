package com.backoven.catdogshelter.domain.post.query.dto;

/* 자유게시글 목록에 대한 정보를 담아서 전송하는 DTO  */
/* mybatis를 이용해서 조회를 담당하고 entity 없이 DTO만으로도 service, repository 등에도 사용할 수 있어 entity를 사용 안함 */
/* 외래키 user_id와 head_id는 xml 쿼리문에서 조인한 후 결과를 writeName에 저장 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostInventoryDTO {
    private int id;
    private String title;
    private String created_at;
    private String updated_at;
    private int view;
    private String writeType;
    private String writeName;

}