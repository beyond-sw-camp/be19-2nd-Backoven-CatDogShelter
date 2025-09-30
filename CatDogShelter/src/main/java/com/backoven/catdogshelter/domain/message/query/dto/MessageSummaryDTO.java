package com.backoven.catdogshelter.domain.message.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MessageSummaryDTO {
    @Schema(description = "메시지 번호", example = "12")
    private int id;
    @Schema(description = "메시지 작성일", example = "2025.03.08 12:01:23")
    private String createdAt;
    @Schema(description = "받은 사람이 읽음 여부", example = "false")
    private boolean status;

    @Schema(description = "보낸사람 or 받은사람")
    private MessageUserDTO user;    // 보낸 사람 or 받은 사람
}
