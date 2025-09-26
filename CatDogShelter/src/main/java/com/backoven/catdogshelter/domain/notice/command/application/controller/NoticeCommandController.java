package com.backoven.catdogshelter.domain.notice.command.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/notice-posts")
public class NoticeCommandController {

    NoticeCommandService noticeCommandService;

    @Autowired
    public NoticeCommandController(NoticeCommandService noticeCommandService) {
        this.noticeCommandService = noticeCommandService;
    }

    @PostMapping("/")
}
