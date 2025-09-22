-- 목격제보 댓글 더미 데이터 10개
INSERT INTO sightingPostComment (content, created_at, updated_at, is_blinded, is_deleted, post_id, user_id, head_id) VALUES
('저도 어제 그 근처에서 본 것 같아요.', '2025-09-01 10:20', NULL, FALSE, FALSE, 1, 2, NULL),
('혹시 사진 원본도 있나요?', '2025-09-02 11:15', '2025-09-02 12:00', FALSE, FALSE, 2, 3, NULL),
('저희 동네에서 자주 보던 고양이랑 닮았네요.', '2025-09-03 14:05', NULL, FALSE, FALSE, 3, 4, NULL),
('목격 장소가 구체적으로 어디인가요?', '2025-09-04 09:40', NULL, TRUE, FALSE, 4, 5, NULL),
('근처 주민들에게 알려드리면 도움이 될 것 같아요.', '2025-09-05 13:25', '2025-09-05 14:10', FALSE, FALSE, 5, 6, NULL),
('저희 보호소에도 비슷한 제보가 있었습니다.', '2025-09-06 16:00', NULL, FALSE, FALSE, 6, NULL, 2),
('동물 등록번호 확인해보시는 게 좋습니다.', '2025-09-07 10:50', NULL, FALSE, FALSE, 7, NULL, 3),
('혹시 공격적인 행동은 없었나요?', '2025-09-08 15:35', NULL, FALSE, TRUE, 8, NULL, 4),
('저도 같은 장소 지나갔는데 못 봤습니다.', '2025-09-09 18:20', '2025-09-09 19:00', FALSE, FALSE, 9, 7, NULL),
('사진을 조금 더 선명하게 올려주시면 도움이 될 것 같아요.', '2025-09-10 20:10', NULL, TRUE, FALSE, 10, 8, NULL);
