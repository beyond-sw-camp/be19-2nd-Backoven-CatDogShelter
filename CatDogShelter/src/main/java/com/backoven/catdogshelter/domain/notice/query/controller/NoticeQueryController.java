package com.backoven.catdogshelter.domain.notice.query.controller;

import com.backoven.catdogshelter.domain.notice.query.dto.NoticeQueryDTO;
import com.backoven.catdogshelter.domain.notice.query.service.NoticeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping("/notice-posts")
public class NoticeQueryController {

    private NoticeQueryService noticeQueryService;

    @Autowired
    public NoticeQueryController(NoticeQueryService noticeQueryService) {
        this.noticeQueryService = noticeQueryService;
    }

    @GetMapping("/notices")
    public ResponseEntity<List<NoticeQueryDTO>> selectNotices() {

        List<NoticeQueryDTO> notices = noticeQueryService.selectNotices();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8"))
        );

        return ResponseEntity.ok().headers(headers).body(notices);
    }
}
