package com.backoven.catdogshelter.domain.notice.command.application.controller;

import com.backoven.catdogshelter.domain.notice.command.application.dto.*;
import com.backoven.catdogshelter.domain.notice.command.application.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/notice-posts")
@RequiredArgsConstructor
public class NoticeCommandController {

    private final NoticeService noticeService;

    // 공지사항 생성
    @PostMapping("/write")
    public ResponseEntity<Void> create(@RequestBody NoticeDTO dto) {
        noticeService.writeNotice(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 게시글 수정
    @PutMapping("/modify/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody NoticeUpdateDTO dto) {
        noticeService.modifyNotice(id, dto);
        return ResponseEntity.noContent().build();
    }

    // 소프트 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        noticeService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    // 좋아요
    @PostMapping("/{id}/likes")
    public ResponseEntity<Map<String,Object>> like(@PathVariable Long id, @RequestBody LikeRequest req) {
        long count = noticeService.like(id, req);
        return ResponseEntity.ok(Map.of("likeCount", count));
    }

    // 좋아요 취소
    @DeleteMapping("/{id}/likes")
    public ResponseEntity<Map<String,Object>> unlike(@PathVariable Long id, @RequestBody LikeRequest req) {
        long count = noticeService.unlike(id, req);
        return ResponseEntity.ok(Map.of("likeCount", count));
    }

}
