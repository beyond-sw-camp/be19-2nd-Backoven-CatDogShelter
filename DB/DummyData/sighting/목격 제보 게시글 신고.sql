-- 목격 제보 게시글 신고 (sightingPostReport) 더미 데이터 10개
INSERT INTO sightingPostReport (category, etc_detail, created_at, status, post_id, user_id, head_id) VALUES
-- 일반 유저 신고
('SPAM', NULL, '2025-09-01 12:00', FALSE, 1, 3, NULL),
('ABUSE', NULL, '2025-09-02 15:30', TRUE, 2, 4, NULL),
('FRAUD', NULL, '2025-09-03 17:45', FALSE, 3, 5, NULL),
('MISINFO', NULL, '2025-09-04 19:10', FALSE, 4, 6, NULL),
('ETC', '허위 정보 의심됨', '2025-09-05 09:20', TRUE, 5, 7, NULL),

-- 보호소장이 신고
('SPAM', NULL, '2025-09-06 13:15', FALSE, 6, NULL, 2),
('ABUSE', NULL, '2025-09-07 10:40', TRUE, 7, NULL, 3),
('ETC', '불필요한 홍보성 게시물', '2025-09-08 14:55', FALSE, 8, NULL, 5),
('MISINFO', NULL, '2025-09-09 16:25', FALSE, 9, NULL, 7),
('FRAUD', NULL, '2025-09-10 18:05', TRUE, 10, NULL, 9);
