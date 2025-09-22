-- 목격제보 댓글 신고 더미 데이터 10개
INSERT INTO sightingPostCommentReport (category, etc_detail, created_at, status, comment_id, user_id, head_id) VALUES
('SPAM', NULL, '2025-09-01 12:30', FALSE, 1, 3, NULL),
('ABUSE', NULL, '2025-09-02 14:10', TRUE, 2, 4, NULL),
('PORN', NULL, '2025-09-03 09:45', FALSE, 3, 5, NULL),
('FRAUD', NULL, '2025-09-04 16:20', FALSE, 4, 6, NULL),
('MISINFO', NULL, '2025-09-05 18:05', TRUE, 5, 7, NULL),
('ETC', '허위 신고 의심', '2025-09-06 11:15', FALSE, 6, NULL, 2),
('SPAM', NULL, '2025-09-07 13:40', TRUE, 7, NULL, 3),
('ABUSE', NULL, '2025-09-08 15:50', FALSE, 8, NULL, 4),
('ETC', '욕설 포함', '2025-09-09 17:25', FALSE, 9, 8, NULL),
('MISINFO', NULL, '2025-09-10 19:00', TRUE, 10, NULL, 5);
