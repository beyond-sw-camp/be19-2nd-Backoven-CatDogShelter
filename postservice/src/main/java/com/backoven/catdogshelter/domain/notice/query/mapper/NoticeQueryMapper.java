// src/main/java/com/backoven/catdogshelter/domain/notice/query/mapper/NoticeQueryMapper.java
package com.backoven.catdogshelter.domain.notice.query.mapper;

import com.backoven.catdogshelter.domain.notice.query.dto.NoticeDetailDTO;
import com.backoven.catdogshelter.domain.notice.query.dto.NoticeFileDTO;
import com.backoven.catdogshelter.domain.notice.query.dto.NoticeListItemDTO;
import com.backoven.catdogshelter.domain.notice.query.dto.NoticeSearchCond;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeQueryMapper {

    // 공지 단건(파일 제외, 좋아요 수 포함)
    NoticeDetailDTO findNoticeById(@Param("id") Integer id);

    // 해당 공지의 파일 목록
    List<NoticeFileDTO> findFilesByNoticeId(@Param("noticeId") Integer noticeId);

    List<NoticeListItemDTO> searchNotices(@Param("cond") NoticeSearchCond cond);

    long countNotices(@Param("cond") NoticeSearchCond cond);
}
