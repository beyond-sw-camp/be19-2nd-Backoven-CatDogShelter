package com.backoven.catdogshelter.domain.message.command.application.controller;

import com.backoven.catdogshelter.domain.message.command.application.dto.RequestMessageDTO;
import com.backoven.catdogshelter.domain.message.command.application.service.AMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/message")
public class AMessageController {
    private final AMessageService aMessageService;

    @Autowired
    public AMessageController(@Qualifier("AMessageServiceImpl") AMessageService aMessageService) {
        this.aMessageService = aMessageService;
    }

    // 쪽지 보내기
    @PostMapping("/message")
    public ResponseEntity<?> registMessage(@RequestBody RequestMessageDTO newMessageDTO) {
        int messageId = aMessageService.registMessage(newMessageDTO);

        return ResponseEntity
                .created(URI.create("/message/" + messageId))   // Response Header 중 "Location"에 담겨 돌아옴
                .build();
    }

    // 쪽지 삭제하기
    @DeleteMapping("/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable int messageId) {
        aMessageService.removeMessage(messageId);

        return ResponseEntity.noContent().build();
    }
}
