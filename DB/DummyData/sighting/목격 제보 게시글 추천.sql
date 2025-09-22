-- 목격 제보 게시글 추천 (sightingPostLiked) 더미 데이터 10개

INSERT INTO sightingPostLiked (post_id, user_id, head_id) VALUES
-- 일반 유저가 좋아요sightingpostliked
(1, 2, NULL),
(2, 1, NULL),
(5, 3, NULL),
(7, 5, NULL),
(9, 7, NULL),

-- 보호소장이 좋아요
(3, NULL, 2),
(4, NULL, 3),
(6, NULL, 5),
(8, NULL, 7),
(10, NULL, 9);