package com.backoven.catdogshelter.domain.notice.query.service;

import com.backoven.catdogshelter.domain.notice.query.dto.NoticeQueryDTO;
import com.backoven.catdogshelter.domain.notice.query.mapper.NoticeQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeQueryService {

    private NoticeQueryMapper noticeQueryMapper;

    @Autowired
    public NoticeQueryService(NoticeQueryMapper noticeQueryMapper) {
        this.noticeQueryMapper = noticeQueryMapper;
    }

    public List<NoticeQueryDTO> selectNotices() {
        return noticeQueryMapper.selectNotices();
    }
}
