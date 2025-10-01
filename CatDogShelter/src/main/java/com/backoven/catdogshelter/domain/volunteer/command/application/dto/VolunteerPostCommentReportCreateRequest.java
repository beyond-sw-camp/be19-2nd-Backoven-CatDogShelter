package com.backoven.catdogshelter.domain.volunteer.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 댓글 신고 요청 DTO
@Getter @Setter
@ToString
public class VolunteerPostCommentReportCreateRequest {
    private String category;
    private String etcDetail;
    private Integer commentId;
    private Integer userId;
    private Integer headId;
}
