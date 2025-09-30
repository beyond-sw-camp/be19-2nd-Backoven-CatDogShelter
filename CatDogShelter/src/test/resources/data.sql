-- 자유 게시글
INSERT INTO post (title, content, created_at, updated_at, is_deleted, user_id, head_id)
VALUES
    ('테스트 게시글1', '내용1', '2025-09-01 10:00:00', NULL, 0, 1, 1),
    ('테스트 게시글2', '내용2', '2025-09-02 11:00:00', NULL, 0, 2, 1);

-- 댓글
INSERT INTO postcomment (content, created_at, updated_at, is_deleted, post_id, user_id, head_id)
VALUES
    ('댓글1', '2025-09-01 11:00:00', NULL, 0, 1, 1, 1),
    ('댓글2', '2025-09-02 12:00:00', NULL, 0, 1, 2, 1);

-- 게시글 신고
INSERT INTO postreport (category, post_id, created_at, etc_detail, head_id, user_id)
VALUES
    ('SPAM', 1, '2025-09-02 12:30:00', '스팸 신고', 1, 1),
    ('ABUSE', 1, '2025-09-02 12:35:00', '욕설 신고', 1, 2);

-- 댓글 신고
INSERT INTO postcommentreport (category, comment_id, created_at, etc_detail, head_id, user_id)
VALUES
    ('SPAM', 1, '2025-09-02 13:00:00', '댓글 스팸 신고', 1, 1),
    ('ABUSE', 2, '2025-09-02 13:05:00', '댓글 욕설 신고', 1, 2);
