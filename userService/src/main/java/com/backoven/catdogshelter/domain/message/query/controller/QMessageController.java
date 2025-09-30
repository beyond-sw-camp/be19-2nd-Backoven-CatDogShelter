package com.backoven.catdogshelter.domain.message.query.controller;

import com.backoven.catdogshelter.domain.message.query.dto.MessageDetailsDTO;
import com.backoven.catdogshelter.domain.message.query.dto.MessageSummaryDTO;
import com.backoven.catdogshelter.domain.message.query.dto.RequestMessageSummaryDTO;
import com.backoven.catdogshelter.domain.message.query.service.QMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@Slf4j
public class QMessageController {
    private final QMessageService qMessageService;

    @Autowired
    public QMessageController(@Qualifier("QMessageServiceImpl") QMessageService qMessageService) {// 왜 인터페이스도 빈으로 잡히지?
        this.qMessageService = qMessageService;
    }


    // 본인한테 온 메시지 확인 -> 목록 조회
    @GetMapping("/summary-subject")
    public List<MessageSummaryDTO> findMessageSummarySubject(@ModelAttribute RequestMessageSummaryDTO subjectUser) {
        log.info("input: {}", subjectUser);
        return qMessageService.findMessageSummarySubject(subjectUser);
    }

    // 본인이 보낸 쪽지 조회 -> 목록 조회
    @GetMapping("/summary-send")
    public List<MessageSummaryDTO> findMeesageSummarySend(@ModelAttribute RequestMessageSummaryDTO sendUser) {
        return qMessageService.findMessageSummarySend(sendUser);
    }

    // 메시지 상세 조회
    @GetMapping("/{messageId}")
    public MessageDetailsDTO findMessageDetails(@PathVariable int messageId) {
        return qMessageService.findMessageDetails(messageId);
    }
}
