package com.backoven.catdogshelter.domain.notice.command.application.service;

public interface NoticeLikeService {

    /**
     * 사용자(user) 기준 좋아요 토글
     * @return 토글 결과 최종 상태 (true면 좋아요 ON, false면 OFF)
     */
    boolean toggleByUser(Integer noticeId, Integer userId);

    /**
     * 보호소장(head) 기준 좋아요 토글
     * @return 토글 결과 최종 상태 (true면 좋아요 ON, false면 OFF)
     */
    boolean toggleByHead(Integer noticeId, Integer headId);

    /**
     * 현재 좋아요 여부 조회(선택)
     */
    boolean isLikedByUser(Integer noticeId, Integer userId);
    boolean isLikedByHead(Integer noticeId, Integer headId);
}
