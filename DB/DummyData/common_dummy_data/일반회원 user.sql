-- 일반회원 (user) 10명
INSERT INTO user (user_name, user_account, user_password, e_mail, detail_address, answer, user_phone,
                  cumulative_volunteer_time, month_volunteer_time, volunteer_count,
                  deactivation_times, user_status, activation_date, rating_id, sigungu_id, questionCategory_id) VALUES
('박지수', 'parkjs', 'pass123!', 'parkjs@example.com', '서울 종로구 한옥마을길 5', '짱구', '010-1234-5678',
 0, 0, 0, 0, 'GENERAL', '2025-06-01', 0, 1, 1),

('최민호', 'choiminho', 'Qwe123!@#', 'minho@example.com', '서울 중구 을지로 10', '초코', '010-2345-6789',
 15, 5, 3, 1, 'GENERAL', '2025-05-15', 1, 2, 2),

('이소영', 'leesy', 'Abcd987$', 'soyoung@example.com', '부산 서구 구덕로 23', '해운대초등학교', '010-3456-7890',
 30, 10, 5, 2, 'GENERAL', '2025-04-20', 2, 6, 3),

('김하진', 'kimhj', 'ZzYyXx123$', 'hajin@example.com', '대구 중구 산격로 45', '김치찌개', '010-4567-8901',
 65, 20, 8, 3, 'BLACK', '2025-03-10', 3, 16, 4),

('정다은', 'jeongde', 'LoveDogs1!', 'daeun@example.com', '인천 남구 학익동 78', '파란색', '010-5678-9012',
 100, 30, 12, 4, 'CANCEL', '2025-02-01', 3, 19, 5),

('한유진', 'hanyj', 'Cat&Dog@2025', 'yujin@example.com', '광주 동구 금남로 2가 34', '지민', '010-6789-0123',
 5, 1, 1, 0, 'GENERAL', '2025-07-01', 0, 22, 6),

('오승환', 'oseungh', 'P@ssSeoul9', 'seungh@example.com', '대전 동구 대전로 56', '부산', '010-7890-1234',
 40, 12, 6, 2, 'GENERAL', '2025-06-10', 2, 6, 7),

('서민재', 'seominj', 'HappyDog#4', 'seominj@example.com', '울산 중구 옥교동 101', '김영희', '010-8901-2345',
 75, 22, 9, 3, 'GENERAL', '2025-05-20', 3, 7, 8),

('이태훈', 'leeth', 'Sunshine!7', 'leeth@example.com', '경기 수원시 장안구 정자동 8', '일본', '010-9012-3456',
 12, 4, 2, 0, 'GENERAL', '2025-07-15', 1, 8, 9),

('박서윤', 'parksy', 'MoonCat$5', 'parksy@example.com', '강원 춘천시 중앙로 12', '박선생님', '010-0123-4567',
 25, 6, 4, 1, 'GENERAL', '2025-07-05', 1, 9, 10);