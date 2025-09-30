package com.backoven.catdogshelter.domain.post.query.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseDTO {
    private int status;          // HTTP 상태코드
    private String error;        // 상태명 (Bad Request, Not Found...)
    private String message;      // 상세 메시지
    private String path;         // 요청 경로
    private LocalDateTime timestamp;

    // 편의용 팩토리
    public static ErrorResponseDTO of(org.springframework.http.HttpStatus status,
                                      String message,
                                      String path) {
        return ErrorResponseDTO.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(path)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
