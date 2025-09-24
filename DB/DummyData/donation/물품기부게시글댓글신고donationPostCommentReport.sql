-- 물품기부게시글댓글 신고donationPostCommentReport (댓글 신고)



INSERT INTO donationPostCommentReport (category, etc_detail, created_at, status, comment_id, user_id, head_id)

VALUES

('SPAM', NULL, '2025-09-02 10:00', FALSE, 1, 2, NULL),

('ABUSE', '비속어 포함', '2025-09-03 12:20', TRUE, 2, 3, NULL),

('PORN', NULL, '2025-09-04 15:40', FALSE, 3, NULL, 1),

('FRAUD', '광고성 댓글', '2025-09-05 17:25', FALSE, 4, 4, NULL),

('MISINFO', '사실과 다른 내용', '2025-09-06 09:15', TRUE, 5, NULL, 2),

('ETC', '운영방침 위반', '2025-09-07 14:55', FALSE, 6, 5, NULL);

