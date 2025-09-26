-- 메세지 (message) 10개
INSERT INTO message (content, created_at, status, subject_number,
                     subject_user_id, send_user_id, subject_head_id, send_head_id) VALUES
('서울센터 봉사 신청 문의드립니다.', '2025-09-01 10:00', FALSE, 1, 1, 2, NULL, NULL),
('보호소 입양 절차 안내 부탁드립니다.', '2025-09-02 11:30', TRUE, 2, 2, 3, NULL, NULL),
('부산센터 위치 문의드립니다.', '2025-09-03 12:45', FALSE, 3, 3, NULL, 2, NULL), 
('대구 보호소 관련 문의', '2025-09-04 13:00', TRUE, 4, 4, NULL, 3, NULL),
('후원 방법 알려주세요.', '2025-09-05 14:15', FALSE, 5, 5, NULL, 1, NULL),
('회원 탈퇴 후 재가입 문의드립니다.', '2025-09-06 15:20', TRUE, 6, NULL, NULL, 4, NULL),
('봉사확인서 발급 요청합니다.', '2025-09-07 16:30', FALSE, 7, 1, NULL, NULL, NULL),
('광주 보호소 현황이 궁금합니다.', '2025-09-08 17:45', FALSE, 8, NULL, NULL, 5, NULL),
('입양 가능한 반려동물 리스트 부탁드립니다.', '2025-09-09 18:00', TRUE, 9, NULL, NULL, 6, NULL),
('감사합니다! 잘 부탁드립니다.', '2025-09-10 19:20', FALSE, 10, NULL, NULL, 7, NULL);