-- 실종신고 게시글 추천 missingPostLiked

INSERT INTO missingPostLiked 
(id, post_id, user_id, head_id)
VALUES
(1, 1, 2, NULL),
(2, 1, 3, NULL),
(3, 1, NULL, 1),
(4, 2, 4, NULL),
(5, 2, 5, NULL),
(6, 2, NULL, 2),
(7, 3, 6, NULL),
(8, 3, 7, NULL),
(9, 3, NULL, 3),
(10, 4, 8, NULL),
(11, 4, 9, NULL),
(12, 4, NULL, 4),
(13, 5, 10, NULL),
(14, 5, 1, NULL),
(15, 5, NULL, 5),
(16, 6, 2, NULL),
(17, 6, 3, NULL),
(18, 6, NULL, 6),
(19, 7, 4, NULL),
(20, 7, 5, NULL);