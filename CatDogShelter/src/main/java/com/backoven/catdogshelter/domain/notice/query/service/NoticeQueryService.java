package com.backoven.catdogshelter.domain.notice.query.service;

import com.backoven.catdogshelter.domain.notice.query.dto.NoticePostFileQueryDTO;
import com.backoven.catdogshelter.domain.notice.query.dto.NoticePostQueryDTO;
import com.backoven.catdogshelter.domain.notice.query.dto.NoticeQueryDTO;
import com.backoven.catdogshelter.domain.notice.query.mapper.NoticeQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeQueryService {

    private NoticeQueryMapper noticeQueryMapper;

    @Autowired
    public NoticeQueryService(NoticeQueryMapper noticeQueryMapper) {
        this.noticeQueryMapper = noticeQueryMapper;
    }

    // 공지사항 전체 목록 조회
    public List<NoticeQueryDTO> selectNotices() {
        return noticeQueryMapper.selectNotices();
    }

    // 공지사항 검색 목록 조회(제목 || 내용)
    public List<NoticeQueryDTO> selectNoticesByKeyword(String keyword) {
        return noticeQueryMapper.selectNoticesByKeyword(keyword);
    }

    // 공지사항 상세 조회
    public NoticePostQueryDTO selectNotice(int id) {

        List<NoticePostFileQueryDTO> files = noticeQueryMapper.selectNoticePostFiles(id);
        NoticePostQueryDTO notice = noticeQueryMapper.selectNotice(id);

        notice.setFiles(files);

        return notice;
    }
}
