package com.backoven.catdogshelter.domain.donation.query.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DonationPostQueryDTO {
    private Integer id;          // 게시글 번호 (PK)
    private String title;        // 제목
    private String content;      // 내용
    private String createdAt;    // 작성일자
    private String updatedAt;    // 수정일자
    private Integer view;        // 조회 수
    private String shelterName;  // 보호소 이름 (company_name)
    private Integer headId;      // 보호소 ID
    private String userName;     // 보호소장 이름 (ceo_name)
    private Integer likeCount;   //추천수

    private List<DonationPostFileDTO> files;
    private List<DonationPostCommentDTO> comments;
}
