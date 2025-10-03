// NoticeService.java
package com.backoven.catdogshelter.domain.notice.command.application.service;

import com.backoven.catdogshelter.domain.notice.command.application.dto.NoticeDTO;
import com.backoven.catdogshelter.domain.notice.command.application.dto.NoticeUpdateDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NoticeService {
    long writeNotice(NoticeDTO dto, List<MultipartFile> files);
    void modifyNotice(Long id, NoticeUpdateDTO dto, List<MultipartFile> newFiles, List<Integer> deleteFileIds);
    /**
     * 좋아요 토글 (userId 또는 headId 중 하나만 전달)
     */
    void toggleLike(Integer noticeId, Integer userId, Integer headId);
}
