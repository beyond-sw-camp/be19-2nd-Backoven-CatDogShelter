package com.backoven.catdogshelter.domain.message.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestMessageDTO {
    @Schema(description = "메시지 내용", example = "안녕")
    private String content;
    @Schema(description = "답장할 메시지 번호", example = "12")
    private Integer subjectNumber;
    @Schema(description = "받을 사람이 유저인 경우 유저 번호(null 허용, subjectHeadId와 배타적 관계)")
    private Integer subjectUserId;
    @Schema(description = "보낸 사람이 유저인 경우 유저 번호(null 허용, sendHeadId와 배타적 관계)")
    private Integer sendUserId;
    @Schema(description = "받은 사람이 보호소 회원인 경우 보호소 번호(null 허용, subjectUserId와 배타적 관계)")
    private Integer subjectHeadId;
    @Schema(description = "보낸 사람이 보호소 회원인 경우 보호소 번호(null 허용, sendUserId와 배타적 관계)")
    private Integer sendHeadId;
}
