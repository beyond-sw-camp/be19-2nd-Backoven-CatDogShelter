-- 실종신고 게시글 댓글 missingPostComment

INSERT INTO missingPostComment 
(id, content, created_at, updated_at, is_blinded, is_deleted, post_id, user_id, head_id)
VALUES
(1, '저도 근처에서 본 것 같아요. 빨리 찾으시길 바랍니다.', '2025-09-01 13:00', NULL, FALSE, FALSE, 1, 2, NULL),
(2, '강아지가 혹시 목줄을 하고 있었나요?', '2025-09-01 14:10', NULL, FALSE, FALSE, 1, 3, NULL),
(3, '저희 동네 전단지 붙여드릴까요?', '2025-09-01 15:20', NULL, FALSE, FALSE, 1, NULL, 1),
(4, '고양이 특징이 더 있을까요?', '2025-09-02 15:00', NULL, FALSE, FALSE, 2, 4, NULL),
(5, '제가 근처를 자주 다니는데 신경 써볼게요.', '2025-09-02 16:30', NULL, FALSE, FALSE, 2, 5, NULL),
(6, '비슷한 고양이를 본 적 있습니다.', '2025-09-02 17:10', NULL, FALSE, FALSE, 2, NULL, 2),
(7, '혹시 CCTV 확인해보셨나요?', '2025-09-03 10:00', NULL, FALSE, FALSE, 3, 6, NULL),
(8, '근처 보호소에 신고해보세요.', '2025-09-03 11:15', NULL, FALSE, FALSE, 3, 7, NULL),
(9, '저희 보호소에서 확인해보겠습니다.', '2025-09-03 12:00', NULL, FALSE, FALSE, 3, NULL, 3),
(10, '길고양이라면 다시 나타날 수도 있어요.', '2025-09-04 12:00', NULL, FALSE, FALSE, 4, 8, NULL),
(11, '저도 본 적 있습니다. 위치 알려드릴게요.', '2025-09-04 12:40', NULL, FALSE, FALSE, 4, 9, NULL),
(12, '근처 주민들에게 물어보는 것도 좋아요.', '2025-09-04 13:20', NULL, FALSE, FALSE, 4, NULL, 4),
(13, '강아지가 겁이 많다니 걱정되네요.', '2025-09-05 18:10', NULL, FALSE, FALSE, 5, 10, NULL),
(14, 'SNS에도 올려보세요.', '2025-09-05 19:00', NULL, FALSE, FALSE, 5, 1, NULL),
(15, '저희 보호소 근처도 확인해드릴게요.', '2025-09-05 19:40', NULL, FALSE, FALSE, 5, NULL, 5),
(16, '고양이가 낯가린다니 더 힘드시겠네요.', '2025-09-06 11:00', NULL, FALSE, FALSE, 6, 2, NULL),
(17, '근처 동물병원에도 연락해보세요.', '2025-09-06 11:45', NULL, FALSE, FALSE, 6, 3, NULL),
(18, '우리 보호소도 확인해보겠습니다.', '2025-09-06 12:20', NULL, FALSE, FALSE, 6, NULL, 6),
(19, '강아지 사진이 있으면 더 도움이 될 것 같아요.', '2025-09-07 09:30', NULL, FALSE, FALSE, 7, 4, NULL),
(20, '제가 근처를 지나다니면서 유심히 보겠습니다.', '2025-09-07 10:10', NULL, FALSE, FALSE, 7, 5, NULL);