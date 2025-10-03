-- 물품기부 게시글 donationPost (게시글)

INSERT INTO donationPost (title, content, created_at, updated_at, view, head_id)

VALUES

('서울사랑보호소 사료 후원 안내', '서울사랑보호소에서 사료 10kg을 확보하여 보호 동물들에게 배분할 예정입니다.', '2025-09-01 10:00', NULL, 12, 1),

('부산해피보호소 고양이 모래 지원', '부산해피보호소에서 고양이 모래 5봉지를 후원받아 사용하게 되었습니다.', '2025-09-02 11:20', NULL, 20, 2),

('대구펫케어센터 하네스 비치', '대구펫케어센터에서 소형견 하네스 3개를 준비했습니다.', '2025-09-03 09:30', NULL, 7, 3),

('인천희망보호소 캣타워 기증', '인천희망보호소에 새 캣타워가 들어와 반려묘들이 사용 중입니다.', '2025-09-04 14:10', NULL, 25, 4),

('광주펫앤케어센터 애견간식 후원', '광주펫앤케어센터에서 후원받은 애견간식을 배분할 예정입니다.', '2025-09-05 08:40', NULL, 15, 5),

('대전포우즈보호소 이동장 추가 비치', '대전포우즈보호소에서 소형견 이동장 2개를 구비했습니다.', '2025-09-06 13:25', NULL, 9, 6),

('울산굿펫센터 담요 후원 안내', '울산굿펫센터에서 겨울 대비 담요 5장을 확보했습니다.', '2025-09-07 15:50', NULL, 18, 7),

('경기동물쉼터 사료 정기 후원 시작', '경기동물쉼터에서 매달 사료 20kg을 확보하여 배급할 계획입니다.', '2025-09-08 12:00', NULL, 30, 8),

('강원러브센터 애견 샴푸 비치', '강원러브센터에서 새 애견샴푸 10개를 준비했습니다.', '2025-09-09 16:40', NULL, 5, 9),

('충북케어보호소 고양이 캔사료 확보', '충북케어보호소에서 고양이 캔사료 50개를 확보하였습니다.', '2025-09-10 19:10', NULL, 40, 10);




-- 물품기부게시글 댓글 donationPostComment (댓글)



INSERT INTO donationPostComment (content, created_at, updated_at, post_id, user_id, head_id)

VALUES

('좋은 기부 감사합니다!', '2025-09-01 12:00', NULL, 1, 1, NULL),

('필요한 곳에 잘 쓰이겠습니다.', '2025-09-02 13:30', NULL, 2, 2, NULL),

('정말 도움이 됩니다.', '2025-09-03 15:10', NULL, 3, 3, NULL),

('보호소에서 감사드립니다.', '2025-09-04 09:45', NULL, 4, NULL, 1),

('기부 너무 멋져요!', '2025-09-05 14:20', NULL, 5, 4, NULL),

('애들이 좋아하겠네요 ^^', '2025-09-06 11:50', NULL, 6, 5, NULL),

('필요한 시점에 딱 맞는 기부네요.', '2025-09-07 10:25', NULL, 7, NULL, 2),

('항상 감사합니다.', '2025-09-08 16:10', NULL, 8, 6, NULL),

('응원합니다!', '2025-09-09 18:40', NULL, 9, NULL, 3),

('정말 소중한 나눔이에요.', '2025-09-10 20:15', NULL, 10, 7, NULL),

('저도 다음에 사료 기부하겠습니다!', '2025-09-11 09:20', NULL, 1, 8, NULL),
('보호소에 큰 도움이 되겠어요 ^^', '2025-09-11 10:05', NULL, 2, NULL, 2),
('고양이들이 정말 좋아하겠네요!', '2025-09-12 14:15', NULL, 3, 9, NULL),
('늘 기부해주셔서 감사드립니다.', '2025-09-12 16:45', NULL, 4, NULL, 3),
('이동장 필요했는데 정말 감사합니다.', '2025-09-13 11:30', NULL, 5, 10, NULL),
('보호소에서 잘 쓰겠습니다!', '2025-09-13 15:10', NULL, 6, NULL, 4),
('앞으로도 함께해요!', '2025-09-14 13:50', NULL, 7, 1, NULL),
('따뜻한 나눔 정말 멋지십니다.', '2025-09-14 18:20', NULL, 8, NULL, 5),
('저도 봉사활동 참여하고 싶네요!', '2025-09-15 09:00', NULL, 9, 2, NULL),
('동물들에게 큰 힘이 될 것 같습니다.', '2025-09-15 20:40', NULL, 10, NULL, 6);




-- 물품기부게시글댓글 신고donationPostCommentReport (댓글 신고)



INSERT INTO donationPostCommentReport (category, etc_detail, created_at, status, comment_id, user_id, head_id)

VALUES

('SPAM', NULL, '2025-09-02 10:00', FALSE, 1, 2, NULL),

('ABUSE', '비속어 포함', '2025-09-03 12:20', TRUE, 2, 3, NULL),

('PORN', NULL, '2025-09-04 15:40', FALSE, 3, NULL, 1),

('FRAUD', '광고성 댓글', '2025-09-05 17:25', FALSE, 4, 4, NULL),

('MISINFO', '사실과 다른 내용', '2025-09-06 09:15', TRUE, 5, NULL, 2),

('ETC', '운영방침 위반', '2025-09-07 14:55', FALSE, 6, 5, NULL);


-- 물품기부 게시글 추천 donationPostLiked (추천)



INSERT INTO donationPostLiked (post_id, user_id, head_id)

VALUES

(1, 1, NULL),

(1, 2, NULL),

(2, NULL, 1),

(3, 3, NULL),

(3, 4, NULL),

(4, NULL, 2),

(5, 5, NULL),

(6, NULL, 3),

(7, 6, NULL),

(8, NULL, 4),

(9, 7, NULL),

(10, 8, NULL),

(10, NULL, 5);



-- 물품기부게시글 첨부파일 donationPostFiles (첨부파일)



INSERT INTO donationPostFiles (file_rename, file_path, uploaded_at, post_id)

VALUES

('food1.png', '/uploads/donation/food1.png', '2025-09-01 10:10', 1),

('sand1.jpg', '/uploads/donation/sand1.jpg', '2025-09-02 11:25', 2),

('harness.pdf', '/uploads/donation/harness.pdf', '2025-09-03 09:40', 3),

('cattower.png', '/uploads/donation/cattower.png', '2025-09-04 14:15', 4),

('snack_box.png', '/uploads/donation/snack_box.png', '2025-09-05 08:45', 5),

('carrier1.jpg', '/uploads/donation/carrier1.jpg', '2025-09-06 13:30', 6),

('blanket.zip', '/uploads/donation/blanket.zip', '2025-09-07 15:55', 7),

('monthly_food.xlsx', '/uploads/donation/monthly_food.xlsx', '2025-09-08 12:05', 8),

('shampoo.docx', '/uploads/donation/shampoo.docx', '2025-09-09 16:45', 9),

('catcanset.png', '/uploads/donation/catcanset.png', '2025-09-10 19:15', 10);