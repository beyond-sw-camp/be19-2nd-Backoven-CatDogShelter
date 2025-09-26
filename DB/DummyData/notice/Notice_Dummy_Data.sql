
-- 공지글 (rating_id = -1)
INSERT INTO notice (title, content, created_at, updated_at, rating_id) 
VALUES
('시스템 점검 안내', '2025년 10월 1일 새벽 1시부터 3시까지 시스템 점검이 진행됩니다.', '2025-09-25 10:00', NULL, -1),
('추석 연휴 고객센터 휴무 안내', '추석 연휴 기간 동안 고객센터 운영이 중단됩니다. 10월 4일부터 정상 운영됩니다.', '2025-09-20 09:00', NULL, -1),
('봉사활동 인증서 발급 안내', '봉사활동 인증서 발급 기능이 새롭게 추가되었습니다. 마이페이지에서 확인 가능합니다.', '2025-09-15 08:30', NULL, -1),
('커뮤니티 이용 규칙 변경 안내', '욕설, 광고성 글은 즉시 삭제되며, 반복 시 계정이 정지됩니다.', '2025-09-10 11:00', NULL, -1),
('신규 보호소 등록 안내', '경기도 동물쉼터가 새롭게 등록되었습니다. 많은 관심 부탁드립니다.', '2025-09-05 15:00', NULL, -1);

-- 공지글 추천 (user_id, head_id 배타적 관계)
INSERT INTO noticeLiked (notice_id, user_id, head_id) 
VALUES
(1, 1, NULL),  -- user 1이 notice 1 좋아요
(1, NULL, 2),  -- head 2가 notice 1 좋아요
(2, 3, NULL),
(2, NULL, 1),
(3, 5, NULL),
(4, NULL, 3),
(5, 6, NULL);

-- 공지사항 첨부 파일 더미 데이터
INSERT INTO noticeFiles (file_rename, file_path, uploaded_at, notice_id) 
VALUES
('system_maintenance.pdf', '/uploads/notice/system_maintenance.pdf', '2025-09-25 10:05', 1),
('chuseok_holiday.png', '/uploads/notice/chuseok_holiday.png', '2025-09-20 09:05', 2),
('certificate_guide.pdf', '/uploads/notice/certificate_guide.pdf', '2025-09-15 08:35', 3),
('community_rules_update.docx', '/uploads/notice/community_rules_update.docx', '2025-09-10 11:05', 4),
('new_shelter_info.jpg', '/uploads/notice/new_shelter_info.jpg', '2025-09-05 15:05', 5);