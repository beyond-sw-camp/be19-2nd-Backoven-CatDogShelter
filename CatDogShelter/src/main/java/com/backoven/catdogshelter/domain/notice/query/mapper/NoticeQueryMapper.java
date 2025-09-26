package com.backoven.catdogshelter.domain.notice.query.mapper;

import com.backoven.catdogshelter.domain.notice.query.dto.NoticeQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeQueryMapper {
    List<NoticeQueryDTO> selectNotices();
}
