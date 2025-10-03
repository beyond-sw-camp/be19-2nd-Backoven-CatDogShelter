package com.backoven.catdogshelter.domain.notice.command.application.service;

import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity.NoticeEntity;
import com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity.NoticeLikedEntity;
import com.backoven.catdogshelter.domain.notice.command.domain.repository.NoticeLikedRepository;
import com.backoven.catdogshelter.domain.notice.command.domain.repository.NoticeRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeLikeServiceImpl implements NoticeLikeService {

    private final NoticeRepository noticeRepository;
    private final NoticeLikedRepository noticeLikedRepository;
    private final EntityManager em;

    @Override
    public boolean toggleByUser(Integer noticeId, Integer userId) {
        // 공지 존재 확인 (존재 안하면 400/404 성격)
        NoticeEntity notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("공지 없음. id=" + noticeId));

        boolean exists = noticeLikedRepository.existsByNoticeIdAndUserId(noticeId, userId);
        if (exists) {
            // 이미 좋아요 → 취소
            noticeLikedRepository.deleteByNoticeIdAndUserId(noticeId, userId);
            return false;
        } else {
            // 좋아요 추가 (User 전용, Head는 null)
            NoticeLikedEntity like = new NoticeLikedEntity();
            like.setNotice(notice);
            like.setUser(em.getReference(UserEntity.class, userId));     // 프록시 참조로 FK만 설정
            like.setHead(null);
            noticeLikedRepository.save(like);
            return true;
        }
    }

    @Override
    public boolean toggleByHead(Integer noticeId, Integer headId) {
        NoticeEntity notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("공지 없음. id=" + noticeId));

        boolean exists = noticeLikedRepository.existsByNoticeIdAndHeadId(noticeId, headId);
        if (exists) {
            // 이미 좋아요 → 취소
            noticeLikedRepository.deleteByNoticeIdAndHeadId(noticeId, headId);
            return false;
        } else {
            // 좋아요 추가 (Head 전용, User는 null)
            NoticeLikedEntity like = new NoticeLikedEntity();
            like.setNotice(notice);
            like.setHead(em.getReference(ShelterheadEntity.class, headId));
            like.setUser(null);
            noticeLikedRepository.save(like);
            return true;
        }
    }

    @Override
    public boolean isLikedByUser(Integer noticeId, Integer userId) {
        return noticeLikedRepository.existsByNoticeIdAndUserId(noticeId, userId);
    }

    @Override
    public boolean isLikedByHead(Integer noticeId, Integer headId) {
        return noticeLikedRepository.existsByNoticeIdAndHeadId(noticeId, headId);
    }
}
