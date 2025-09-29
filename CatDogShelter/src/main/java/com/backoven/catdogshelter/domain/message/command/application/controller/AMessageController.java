package com.backoven.catdogshelter.domain.message.command.application.controller;

import com.backoven.catdogshelter.domain.message.command.application.dto.RequestMessageDTO;
import com.backoven.catdogshelter.domain.message.command.application.service.AMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/message")
@Tag(name = "메시지 CUD API")
public class AMessageController {
    private final AMessageService aMessageService;

    @Autowired
    public AMessageController(@Qualifier("AMessageServiceImpl") AMessageService aMessageService) {
        this.aMessageService = aMessageService;
    }

    // 쪽지 보내기
    @PostMapping("/message")
    @Operation(summary = "메시지 보내기", description = "들어온 입력값을 토대로 메시지를 보냅니다.")
    public ResponseEntity<?> registMessage(@RequestBody RequestMessageDTO newMessageDTO) {
        int messageId = aMessageService.registMessage(newMessageDTO);

        return ResponseEntity
                .created(URI.create("/message/" + messageId))   // Response Header 중 "Location"에 담겨 돌아옴
                .build();
    }

    // 쪽지 삭제하기
    @DeleteMapping("/{messageId}")
    @Operation(summary = "메시지 삭제하기", description = "입력값을 id로 가지는 메시지를 삭제합니다.")
    public ResponseEntity<?> deleteMessage(@PathVariable int messageId) {
        aMessageService.removeMessage(messageId);

        return ResponseEntity.noContent().build();
    }
}
