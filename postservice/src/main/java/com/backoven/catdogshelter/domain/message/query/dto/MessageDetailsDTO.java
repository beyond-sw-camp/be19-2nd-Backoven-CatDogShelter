package com.backoven.catdogshelter.domain.message.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MessageDetailsDTO {
    @Schema(description = "메시지 번호", example = "12")
    private int id;
    @Schema(description = "메시지 내용", example = "안녕")
    private String content;
    @Schema(description = "메시지 작성일", example = "2025.03.08 12:01:23")
    private String createdAt;

    @Schema(description = "보낸 사람")
    private MessageUserDTO sendUser;        // 보낸 사람
    @Schema(description = "받은 사람")
    private MessageUserDTO subjectUser;     // 받은 사람

    @Schema(description = "답장할 쪽지 번호", example = "10")
    private Integer subjectNumber;          // 이전 쪽지 번호

}
