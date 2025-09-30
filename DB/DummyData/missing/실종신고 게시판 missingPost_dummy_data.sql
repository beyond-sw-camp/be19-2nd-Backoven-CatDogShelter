-- 실종신고 게시판missingPost

INSERT INTO missingPost 
(id, title, content, created_at, updated_at, is_blinded, is_deleted, status, detail_address,
 gender, age, registration_num, breed, animal_type, color, weight, lost_date,
 feature, contact, view, user_id, head_id, sigungu_id)
VALUES
(1, '강아지를 잃어버렸습니다', '한강공원에서 산책 중 강아지가 사라졌습니다.', '2025-09-01 12:30', NULL, FALSE, FALSE, FALSE,
 '서울 종로구 세종로 1-1', 'MALE', 3, 123456, '말티즈', 'DOG', '흰색', 4.50, '2025-08-30',
 '겁이 많고 낯선 사람을 무서워합니다.', '010-1234-5678', 0, 1, NULL, 1),

(2, '고양이를 찾습니다', '집 근처 골목에서 실종되었습니다.', '2025-09-02 14:20', NULL, FALSE, FALSE, FALSE,
 '서울 중구 충무로 5', 'FEMALE', 2, 234567, '코숏', 'CAT', '검정색', 3.20, '2025-09-01',
 '꼬리에 흰 줄무늬가 있습니다.', '010-2345-6789', 0, 2, NULL, 2),

(3, '반려견 실종', '부산 서구 구덕로 근처에서 사라졌습니다.', '2025-09-03 09:10', NULL, FALSE, FALSE, TRUE,
 '부산 서구 구덕로 23', 'MALE', 5, 345678, '시바견', 'DOG', '갈색', 7.80, '2025-09-02',
 '목줄을 착용하고 있습니다.', '010-3456-7890', 0, 3, NULL, 6),

(4, '길고양이 실종 신고', '돌보던 길고양이가 안 보입니다.', '2025-09-04 11:05', NULL, FALSE, FALSE, FALSE,
 '대구 중구 산격로 45', 'FEMALE', NULL, NULL, '코숏', 'CAT', '회색', NULL, '2025-09-03',
 '사람을 잘 따릅니다.', '010-4567-8901', 0, NULL, 3, 16),

(5, '강아지 잃어버렸습니다', '인천 학익동 공원에서 사라졌습니다.', '2025-09-05 17:50', NULL, FALSE, FALSE, FALSE,
 '인천 남구 학익동 78', 'MALE', 1, 456789, '푸들', 'DOG', '갈색', 2.10, '2025-09-04',
 '짖음이 심합니다.', '010-5678-9012', 0, 5, NULL, 19),

(6, '반려묘 실종', '광주 동구 금남로 인근에서 잃어버렸습니다.', '2025-09-06 10:15', NULL, FALSE, FALSE, FALSE,
 '광주 동구 금남로 2가 34', 'FEMALE', 4, 567890, '페르시안', 'CAT', '흰색', 4.00, '2025-09-05',
 '사람을 피합니다.', '010-6789-0123', 0, NULL, 5, 22),

(7, '애완견 실종 신고', '대전 동구 대전로에서 사라졌습니다.', '2025-09-07 08:40', NULL, FALSE, FALSE, FALSE,
 '대전 동구 대전로 56', 'MALE', 6, 678901, '골든리트리버', 'DOG', '금색', 12.50, '2025-09-06',
 '순하고 온화합니다.', '010-7890-1234', 0, NULL, 7, 6),

(8, '고양이를 찾습니다', '울산 옥교동 골목에서 실종되었습니다.', '2025-09-08 13:25', NULL, FALSE, FALSE, TRUE,
 '울산 중구 옥교동 101', 'FEMALE', 2, 789012, '러시안블루', 'CAT', '회색', 3.80, '2025-09-07',
 '목에 파란 목걸이를 착용.', '010-8901-2345', 0, 8, NULL, 7),

(9, '강아지 실종', '수원 장안구 정자동에서 사라졌습니다.', '2025-09-09 15:00', NULL, FALSE, FALSE, FALSE,
 '경기 수원시 장안구 정자동 8', 'MALE', 7, 890123, '비글', 'DOG', '흰색&갈색', 9.20, '2025-09-08',
 '활발하고 사람을 잘 따릅니다.', '010-9012-3456', 0, 9, NULL, 8),

(10, '춘천에서 고양이를 잃어버렸습니다', '중앙로 근처에서 사라졌습니다.', '2025-09-10 19:30', NULL, FALSE, FALSE, FALSE,
 '춘천시 중앙로 12', 'FEMALE', 3, 901234, '샴', 'CAT', '갈색', 4.20, '2025-09-09',
 '파란 눈을 가졌습니다.', '010-0123-4567', 0, 10, NULL, 9);




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


-- 실종게시글 파일 업로드 missingPostFiles 

INSERT INTO missingPostFiles (file_rename, file_path, uploaded_at, post_id) VALUES
('dog1_img1.jpg', '/uploads/missing/2025/09/dog1_img1.jpg', '2025-09-01 10:30:00', 1),
('dog1_img2.jpg', '/uploads/missing/2025/09/dog1_img2.jpg', '2025-09-01 10:31:00', 1),

('cat2_img1.jpg', '/uploads/missing/2025/09/cat2_img1.jpg', '2025-09-02 11:15:00', 2),

('dog3_img1.jpg', '/uploads/missing/2025/09/dog3_img1.jpg', '2025-09-03 12:00:00', 3),
('dog3_img2.jpg', '/uploads/missing/2025/09/dog3_img2.jpg', '2025-09-03 12:05:00', 3),

('cat4_img1.jpg', '/uploads/missing/2025/09/cat4_img1.jpg', '2025-09-04 09:45:00', 4),

('dog5_img1.jpg', '/uploads/missing/2025/09/dog5_img1.jpg', '2025-09-05 14:20:00', 5),

('cat6_img1.jpg', '/uploads/missing/2025/09/cat6_img1.jpg', '2025-09-06 16:40:00', 6),
('cat6_img2.jpg', '/uploads/missing/2025/09/cat6_img2.jpg', '2025-09-06 16:42:00', 6),

('dog7_img1.jpg', '/uploads/missing/2025/09/dog7_img1.jpg', '2025-09-07 18:10:00', 7),

('cat8_img1.jpg', '/uploads/missing/2025/09/cat8_img1.jpg', '2025-09-08 19:25:00', 8),

('dog9_img1.jpg', '/uploads/missing/2025/09/dog9_img1.jpg', '2025-09-09 20:50:00', 9),
('dog9_img2.jpg', '/uploads/missing/2025/09/dog9_img2.jpg', '2025-09-09 20:52:00', 9),

('cat10_img1.jpg', '/uploads/missing/2025/09/cat10_img1.jpg', '2025-09-10 21:30:00', 10);