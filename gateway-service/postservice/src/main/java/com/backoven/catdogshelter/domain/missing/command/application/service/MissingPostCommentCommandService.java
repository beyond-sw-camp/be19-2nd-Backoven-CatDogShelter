package com.backoven.catdogshelter.domain.missing.command.application.service;

import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.domain.missing.command.application.dto.CreateMissingCommentRequest;
import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPost;
import com.backoven.catdogshelter.domain.missing.command.domain.aggregate.entity.MissingPostComment;
import com.backoven.catdogshelter.domain.missing.command.domain.repository.MissingPostCommentRepository;
import com.backoven.catdogshelter.domain.missing.command.domain.repository.MissingPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MissingPostCommentCommandService {
    private final MissingPostRepository missingPostRepository;
    private final MissingPostCommentRepository missingPostCommentRepository;

    // 특정 게시글에 댓글 작성
    public Integer createMissingPostComment(CreateMissingCommentRequest dto) {
        MissingPost post = missingPostRepository.findById(dto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        UserEntity user = new UserEntity();
        user.setUserId(dto.getUserId()); // 더미 UserEntity (추후 UserService 연동)

        MissingPostComment comment = new MissingPostComment();
        comment.setContent(dto.getContent());
        comment.setCreatedAt(DateTimeUtil.now());
        comment.setPost(post);
        comment.setUser(user);

        return missingPostCommentRepository.save(comment).getId();
    }

    // 댓글 수정 (작성자 본인만)
    @Transactional
    public void updateMissingPostComment(Integer id, Integer userId, String content) {
        MissingPostComment comment = missingPostCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        // 작성자 본인 검증
        if (comment.getUser() == null || !comment.getUser().getUserId().equals(userId)) {
            throw new IllegalStateException("댓글 작성자만 수정할 수 있습니다.");
        }

        comment.setContent(content);
        comment.setUpdatedAt(DateTimeUtil.now());
    }

    // 댓글 삭제 (작성자 본인만)
    public void deleteMissingPostComment(Integer id, Integer userId) {
        MissingPostComment comment = missingPostCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        // 작성자 본인인지 확인
        if (comment.getUser() == null || !comment.getUser().getUserId().equals(userId)) {
            throw new IllegalStateException("댓글 작성자만 삭제할 수 있습니다.");
        }

        comment.softDelete();
    }
}
