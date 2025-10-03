package com.backoven.catdogshelter.domain.notice.command.application.service;

import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity.NoticeEntity;
import com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity.NoticeLikedEntity;
import com.backoven.catdogshelter.domain.notice.command.domain.repository.NoticeLikedRepository;
import com.backoven.catdogshelter.domain.notice.command.domain.repository.NoticeRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class NoticeLikeServiceImplTest {

    @Mock
    private NoticeRepository noticeRepository;

    @Mock
    private NoticeLikedRepository noticeLikedRepository;

    @Mock
    private EntityManager em;

    // 인터페이스 ❌ → 구현체 ✅
    @InjectMocks
    private NoticeLikeServiceImpl noticeLikeService;

    private NoticeEntity notice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notice = new NoticeEntity();
        notice.setId(1);
    }

    @Test
    void 공지가없으면_예외발생() {
        when(noticeRepository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> noticeLikeService.toggleByUser(1, 100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("공지 없음");
    }

    @Test
    void 좋아요가없으면_추가되고_true반환() {
        when(noticeRepository.findById(1)).thenReturn(Optional.of(notice));
        when(noticeLikedRepository.existsByNoticeIdAndUserId(1, 100)).thenReturn(false);

        UserEntity userProxy = new UserEntity();
        userProxy.setUserId(100);
        when(em.getReference(UserEntity.class, 100)).thenReturn(userProxy);

        boolean result = noticeLikeService.toggleByUser(1, 100);

        assertThat(result).isTrue();
        verify(noticeLikedRepository, times(1)).save(any(NoticeLikedEntity.class));
        verify(noticeLikedRepository, never()).deleteByNoticeIdAndUserId(anyInt(), anyInt());
    }

    @Test
    void 좋아요가이미있으면_삭제되고_false반환() {
        when(noticeRepository.findById(1)).thenReturn(Optional.of(notice));
        when(noticeLikedRepository.existsByNoticeIdAndUserId(1, 100)).thenReturn(true);

        boolean result = noticeLikeService.toggleByUser(1, 100);

        assertThat(result).isFalse();
        verify(noticeLikedRepository, times(1)).deleteByNoticeIdAndUserId(1, 100);
        verify(noticeLikedRepository, never()).save(any());
    }
}