package com.backoven.catdogshelter.domain.post.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/* 예외 발생 시 클라이언트(Postman 등)에 반환할 공통 응답 DTO */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseDTO {

    // HTTP 상태 코드
    private int status;

    // 상태명 (예: "Bad Request", "Not Found")
    private String error;

    // 상세 메시지
    private String message;

    // 요청 경로
    private String path;

    // 발생 시간
    private LocalDateTime timestamp;

    // static 팩토리 메서드 (편의성 제공)
    public static ErrorResponseDTO of(int status, String error, String message, String path) {
        return ErrorResponseDTO.builder()
                .status(status)
                .error(error)
                .message(message)
                .path(path)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
