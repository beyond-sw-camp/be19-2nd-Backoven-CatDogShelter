// NoticeFileRepository.java
package com.backoven.catdogshelter.domain.notice.command.domain.repository;

import com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity.NoticeFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface NoticeFileRepository extends JpaRepository<NoticeFileEntity, Integer> {
    // 보안상, 내 공지의 파일만 삭제되도록 notice_id까지 조건
    List<NoticeFileEntity> findAllByIdInAndNotice_Id(Collection<Integer> ids, Long noticeId);
}
