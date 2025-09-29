package com.backoven.catdogshelter.domain.adoption.command.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdoptionPostCommentReportDTO {
    private Integer id;
    private String category;           // 신고 카테고리 (예: SPAM, ABUSE, ETC)
    private String etcDetail;          // 기타 상세 사유
    private String createdAt;          // 신고 일시
    private Boolean status;            // 신고 상태
    private Integer adoptionPostId;
    private Integer userId;            // 신고자 (유저) ID
    private Integer headId;            // 신고자 (보호소장) ID
    private Integer commentId;         // 신고 대상 댓글 ID
}