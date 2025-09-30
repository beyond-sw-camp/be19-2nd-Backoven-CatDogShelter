package com.backoven.catdogshelter.domain.message.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MessageDetailsDTO {
    private int id;
    private String content;
    private String createdAt;

    private MessageUserDTO sendUser;        // 보낸 사람
    private MessageUserDTO subjectUser;     // 받은 사람

    private Integer subjectNumber;          // 이전 쪽지 번호

}
