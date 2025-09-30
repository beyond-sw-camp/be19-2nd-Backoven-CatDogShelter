package com.backoven.catdogshelter.domain.notice.command.application.service;

import com.backoven.catdogshelter.domain.notice.command.application.dto.LikeRequest;
import com.backoven.catdogshelter.domain.notice.command.application.dto.NoticeDTO;
import com.backoven.catdogshelter.domain.notice.command.application.dto.NoticeUpdateDTO;

public interface NoticeService {
   void writeNotice(NoticeDTO noticeDTO);

    void modifyNotice(Long id, NoticeUpdateDTO dto);

    void softDelete(Long id);

    long like(Long id, LikeRequest req);

    long unlike(Long id, LikeRequest req);
}
