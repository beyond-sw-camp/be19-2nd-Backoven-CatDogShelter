package com.backoven.catdogshelter.domain.notice.command.application.service;

import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.domain.notice.command.application.dto.LikeRequest;
import com.backoven.catdogshelter.domain.notice.command.application.dto.NoticeDTO;
import com.backoven.catdogshelter.domain.notice.command.application.dto.NoticeUpdateDTO;
import com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity.NoticeEntity;
import com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity.NoticeFileEntity;
import com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity.NoticeLikedEntity;
import com.backoven.catdogshelter.domain.notice.command.domain.repository.NoticeFileRepository;
import com.backoven.catdogshelter.domain.notice.command.domain.repository.NoticeLikedRepository;
import com.backoven.catdogshelter.domain.notice.command.domain.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeFileRepository noticeFileRepository;
    private final NoticeLikedRepository noticeLikedRepository;

    // 공지사항 생성
    @Transactional
    @Override
    public void writeNotice(NoticeDTO dto) {
        var e = new NoticeEntity();
        e.setTitle(dto.getTitle());
        e.setContent(dto.getContent());
        e.setCreatedAt(DateTimeUtil.now());
        e.setRatingId(-1L);
        noticeRepository.save(e);

        if (dto.getFiles() != null) {
            for (var f : dto.getFiles()) {
                var nf = new NoticeFileEntity();
                nf.setNoticeId(e.getId());
                nf.setFileRename(f.getFileRename());
                nf.setFilePath(f.getFilePath());
                nf.setUploadedAt(f.getUploadedAt());
                noticeFileRepository.save(nf);
            }
        }
    }

    // 공지사항 수정
    @Transactional
    @Override
    public void modifyNotice(Long id, NoticeUpdateDTO dto) {
        var e = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지를 찾을 수 없습니다. id=" + id));
        e.setTitle(dto.getTitle());
        e.setContent(dto.getContent());
        e.setUpdatedAt(DateTimeUtil.now());
        e.setRatingId(-1L);
    }

    // 공지사항 soft 삭제
    @Transactional
    @Override
    public void softDelete(Long id) {
        var e = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지를 찾을 수 없습니다. id=" + id));
        e.setDeleted(true);
        e.setUpdatedAt(DateTimeUtil.now());
    }

    // 좋아요 추가
    @Transactional
    @Override
    public long like(Long id, LikeRequest req) {
        if ((req.getUserId()==null) == (req.getHeadId()==null))
            throw new IllegalArgumentException("userId 또는 headId 중 하나만 전달하세요.");

        if (req.getUserId()!=null) {
            if (!noticeLikedRepository.existsByNoticeIdAndUserId(id, req.getUserId())) {
                var like = new NoticeLikedEntity();
                like.setNoticeId(id);
                like.setUserId(req.getUserId());
                noticeLikedRepository.save(like);
            }
        } else {
            if (!noticeLikedRepository.existsByNoticeIdAndHeadId(id, req.getHeadId())) {
                var like = new NoticeLikedEntity();
                like.setNoticeId(id);
                like.setHeadId(req.getHeadId());
                noticeLikedRepository.save(like);
            }
        }
        return noticeLikedRepository.countByNoticeId(id);

    }

    // 좋아요 취소
    @Transactional
    @Override
    public long unlike(Long id, LikeRequest req) {
        if ((req.getUserId()==null) == (req.getHeadId()==null))
            throw new IllegalArgumentException("userId 또는 headId 중 하나만 전달하세요.");

        if (req.getUserId()!=null) noticeLikedRepository.deleteByNoticeIdAndUserId(id, req.getUserId());
        else noticeLikedRepository.deleteByNoticeIdAndHeadId(id, req.getHeadId());

        return noticeLikedRepository.countByNoticeId(id);

    }
}
