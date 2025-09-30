package com.backoven.catdogshelter.domain.missing.query.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
//@JsonInclude(JsonInclude.Include.NON_NULL) 해당 값 쓰면 json에서 key=null 안나옴
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MissingPostQueryDTO {
    private Integer id;
    private boolean status;
    private String title;
    private String content;
    private String createdAt;
    private Integer view;
    private String userName;   // 작성자 이름
    private Integer likeCount;     // 좋아요 수 (인기글에서 사용)
    private String userRating; //등급
    private List<MissingPostFileDTO> files;
    private List<MissingPostCommentDTO> comments;
}
