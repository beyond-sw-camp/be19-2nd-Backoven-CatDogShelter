-- 실종신고 게시글 신고 missingPostReport

INSERT INTO missingPostReport 
(id, category, etc_detail, created_at, status, post_id, user_id, head_id)
VALUES
(1, 'SPAM', NULL, '2025-09-05 10:15', FALSE, 1, 2, NULL),
(2, 'ABUSE', '욕설이 포함된 글입니다.', '2025-09-05 11:00', FALSE, 1, 3, NULL),
(3, 'ETC', '허위 제보 같아요.', '2025-09-05 11:30', FALSE, 2, 4, NULL),
(4, 'MISINFO', '실종일자가 실제와 다릅니다.', '2025-09-06 09:20', FALSE, 2, 5, NULL),
(5, 'SPAM', NULL, '2025-09-06 10:00', FALSE, 3, NULL, 1),
(6, 'PORN', '사진이 부적절합니다.', '2025-09-06 10:30', FALSE, 3, 6, NULL),
(7, 'ABUSE', '댓글에서 욕설이 발견됨', '2025-09-07 12:10', FALSE, 4, 7, NULL),
(8, 'ETC', '내용이 중복 신고 같습니다.', '2025-09-07 13:40', FALSE, 4, NULL, 2),
(9, 'SPAM', NULL, '2025-09-08 08:50', FALSE, 5, 8, NULL),
(10, 'FRAUD', '보상금을 노린 사기 의심', '2025-09-08 09:20', FALSE, 5, 9, NULL),
(11, 'MISINFO', '품종 정보가 실제와 다릅니다.', '2025-09-08 10:00', FALSE, 6, 10, NULL),
(12, 'SPAM', NULL, '2025-09-08 10:40', FALSE, 6, NULL, 3),
(13, 'ABUSE', '작성자가 계속 비난을 합니다.', '2025-09-09 14:20', FALSE, 7, 1, NULL),
(14, 'ETC', '허위 신고일 수 있습니다.', '2025-09-09 15:00', FALSE, 7, 2, NULL),
(15, 'FRAUD', '유기견 판매 의심됩니다.', '2025-09-09 16:10', FALSE, 8, NULL, 4);