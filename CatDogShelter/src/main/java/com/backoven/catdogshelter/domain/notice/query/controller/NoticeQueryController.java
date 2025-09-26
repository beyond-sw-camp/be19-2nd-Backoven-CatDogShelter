package com.backoven.catdogshelter.domain.notice.query.controller;

import com.backoven.catdogshelter.domain.notice.query.dto.NoticePostQueryDTO;
import com.backoven.catdogshelter.domain.notice.query.dto.NoticeQueryDTO;
import com.backoven.catdogshelter.domain.notice.query.service.NoticeQueryService;
import com.backoven.catdogshelter.domain.volunteer.query.service.VolunteerPostQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notice-posts")
public class NoticeQueryController {

    private final VolunteerPostQueryService volunteerPostQueryService;
    private NoticeQueryService noticeQueryService;

    @Autowired
    public NoticeQueryController(NoticeQueryService noticeQueryService, VolunteerPostQueryService volunteerPostQueryService) {
        this.noticeQueryService = noticeQueryService;
        this.volunteerPostQueryService = volunteerPostQueryService;
    }

    // 공지사항 전체 목록 조회
    @GetMapping("/notices")
    public ResponseEntity<List<NoticeQueryDTO>> selectNotices() {

        List<NoticeQueryDTO> notices = noticeQueryService.selectNotices();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8"))
        );

        return ResponseEntity.ok().headers(headers).body(notices);
    }

    // 공지사항 검색 목록 조회
    @GetMapping("/search")
    public ResponseEntity<List<NoticeQueryDTO>> selectNoticesByKeyword(
            @RequestParam(required = false) String keyword) {

        List<NoticeQueryDTO> notices = noticeQueryService.selectNoticesByKeyword(keyword);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8"))
        );
        return ResponseEntity.ok().headers(headers).body(notices);
    }

    // 공지사항 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<NoticePostQueryDTO> selectNotice(@PathVariable int id) {

        NoticePostQueryDTO notice = noticeQueryService.selectNotice(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json",
                        Charset.forName("UTF-8")));
        return ResponseEntity.ok().headers(headers).body(notice);
    }
}
