package com.backoven.catdogshelter.domain.notice.query.mapper;

import com.backoven.catdogshelter.domain.notice.query.dto.NoticePostQueryDTO;
import com.backoven.catdogshelter.domain.notice.query.dto.NoticeQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeQueryMapper {

    // 공지사항 전체 목록 조회
    List<NoticeQueryDTO> selectNotices();

    // 공지사항 검색 목록 조회
    List<NoticeQueryDTO> selectNoticesByKeyword(String keyword);

    // 공지사항 상세 조회
    NoticePostQueryDTO selectNotice(int id);
}
