-- 실종신고 게시글 댓글 신고 missingPostCommentReport

INSERT INTO missingPostCommentReport 
(id, category, etc_detail, created_at, status, comment_id, user_id, head_id)
VALUES
(1, 'SPAM', NULL, '2025-09-10 09:00', FALSE, 1, 2, NULL),
(2, 'ABUSE', '비속어가 포함되어 있습니다.', '2025-09-10 09:20', FALSE, 2, 3, NULL),
(3, 'ETC', '허위 제보 같습니다.', '2025-09-10 10:00', FALSE, 3, 4, NULL),
(4, 'MISINFO', '사실과 다른 정보가 적혀 있습니다.', '2025-09-10 10:40', FALSE, 4, 5, NULL),
(5, 'SPAM', NULL, '2025-09-11 08:30', FALSE, 5, NULL, 1),
(6, 'PORN', '부적절한 표현이 있습니다.', '2025-09-11 09:00', FALSE, 6, 6, NULL),
(7, 'ABUSE', '다른 사용자에게 욕설을 합니다.', '2025-09-11 09:30', FALSE, 7, 7, NULL),
(8, 'ETC', '중복 댓글 신고입니다.', '2025-09-11 10:10', FALSE, 8, NULL, 2),
(9, 'SPAM', NULL, '2025-09-12 11:00', FALSE, 9, 8, NULL),
(10, 'FRAUD', '사기성 댓글 같습니다.', '2025-09-12 11:30', FALSE, 10, 9, NULL),
(11, 'MISINFO', '날짜 관련된 정보가 잘못되었습니다.', '2025-09-12 12:00', FALSE, 11, 10, NULL),
(12, 'SPAM', NULL, '2025-09-12 12:40', FALSE, 12, NULL, 3),
(13, 'ABUSE', '상습적으로 비방 댓글을 답니다.', '2025-09-13 08:20', FALSE, 13, 1, NULL),
(14, 'ETC', '허위 신고일 수 있습니다.', '2025-09-13 09:00', FALSE, 14, 2, NULL),
(15, 'FRAUD', '보상 관련 거짓 정보가 있습니다.', '2025-09-13 09:40', FALSE, 15, NULL, 4);