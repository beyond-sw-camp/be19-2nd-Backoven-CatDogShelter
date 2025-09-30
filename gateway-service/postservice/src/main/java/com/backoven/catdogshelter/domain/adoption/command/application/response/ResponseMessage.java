package com.backoven.catdogshelter.domain.adoption.command.application.response;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseMessage {
    private int HttpStatus; // 응답 상태 코드 값
    private String message; // 응답 메세지
    private Map<String, Object> result; // 응답 데이터
}
