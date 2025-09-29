package com.backoven.catdogshelter.domain.message.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MessageSummaryDTO {
    private int id;
    private String createdAt;
    private boolean status;

    private MessageUserDTO user;    // 보낸 사람 or 받은 사람
}
