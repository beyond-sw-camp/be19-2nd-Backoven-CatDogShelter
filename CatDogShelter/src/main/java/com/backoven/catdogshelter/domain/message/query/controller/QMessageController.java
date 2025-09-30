package com.backoven.catdogshelter.domain.message.query.controller;

import com.backoven.catdogshelter.domain.message.query.dto.MessageDetailsDTO;
import com.backoven.catdogshelter.domain.message.query.dto.MessageSummaryDTO;
import com.backoven.catdogshelter.domain.message.query.dto.RequestMessageSummaryDTO;
import com.backoven.catdogshelter.domain.message.query.service.QMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@Slf4j
@Tag(name = "메시지 조회 API")
public class QMessageController {
    private final QMessageService qMessageService;

    @Autowired
    public QMessageController(@Qualifier("QMessageServiceImpl") QMessageService qMessageService) {// 왜 인터페이스도 빈으로 잡히지?
        this.qMessageService = qMessageService;
    }


    // 본인한테 온 메시지 확인 -> 목록 조회
    @GetMapping("/summary-subject")
    @Operation(summary = "받은 메시지 조회", description = "받은 메시지를 조회합니다.")
    public List<MessageSummaryDTO> findMessageSummarySubject(@ModelAttribute RequestMessageSummaryDTO subjectUser) {
        log.info("input: {}", subjectUser);
        return qMessageService.findMessageSummarySubject(subjectUser);
    }

    // 본인이 보낸 쪽지 조회 -> 목록 조회
    @GetMapping("/summary-send")
    @Operation(summary = "보낸 메시지 조회", description = "보낸 메시지를 조회합니다.")
    public List<MessageSummaryDTO> findMeesageSummarySend(@ModelAttribute RequestMessageSummaryDTO sendUser) {
        return qMessageService.findMessageSummarySend(sendUser);
    }

    // 메시지 상세 조회
    @GetMapping("/{messageId}")
    @Operation(summary = "메시지 상세 조회", description = "입력값을 id로 하는 메시지의 내용을 보여줍니다.")
    public MessageDetailsDTO findMessageDetails(@PathVariable int messageId) {
        return qMessageService.findMessageDetails(messageId);
    }
}
