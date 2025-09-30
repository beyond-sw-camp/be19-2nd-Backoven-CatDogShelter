package com.backoven.catdogshelter.domain.volunteer.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class VolunteerPostReportCreateRequest {
    private String category;   // "SPAM","ABUSE","PORN","FRAUD","MISINFO","ETC"
    private String etcDetail;  // ETC일 때만 사용
    private Integer postId;
    private Integer userId;    // userId / headId 중 하나만 채움
    private Integer headId;
}

