package com.backoven.catdogshelter.domain.notice.command.domain.repository;

import com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity.NoticeLikedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeLikedRepository extends JpaRepository<NoticeLikedEntity, Long> {
    boolean existsByNoticeIdAndUserId(Long noticeId, Long userId);
    boolean existsByNoticeIdAndHeadId(Long noticeId, Long headId);
    long countByNoticeId(Long noticeId);
    void deleteByNoticeIdAndUserId(Long noticeId, Long userId);
    void deleteByNoticeIdAndHeadId(Long noticeId, Long headId);
}
