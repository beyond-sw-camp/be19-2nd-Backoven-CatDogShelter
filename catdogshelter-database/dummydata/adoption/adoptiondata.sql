use CatDogShelter;

-- 입양게시판(adoptionPost) 10개
INSERT INTO adoptionPost 
(title, content, created_at, updated_at, user_phone, animal_type, breed, age, color, gender, weight, status, vaccination, neutering, view, is_blind, is_deleted, user_id, head_id, sigungu_id)
VALUES
('사랑스러운 코숏 고양이, 평생 가족 찾아요',
 '2살 된 코숏 아이로, 길에서 구조되어 현재 임시 보호 중입니다. 사람을 무척 좋아하며, 하루 종일 곁에 붙어 있으려 할 정도로 애교가 많습니다. 기본 예방접종은 완료되었고, 중성화 수술도 마쳤습니다. 깨끗하게 모래도 잘 가리고, 큰 병력 없이 건강합니다. 좋은 가족을 만나 따뜻한 집에서 살아가길 바랍니다.',
 '2025-09-01', '2025-09-05', '010-1111-2222', 'CAT', '코숏', 2, '검정', 'FEMALE', 3.20, 'PROTECTING', 'Y', 'Y', 102, FALSE, FALSE, 1, NULL, 1),

('푸들 남아, 활발하고 건강합니다',
 '1살 된 푸들로, 보호소에 들어온 아이입니다. 낯가림이 거의 없고 사람을 잘 따릅니다. 산책을 좋아해서 하루에 두 번 이상 산책해 주시면 가장 행복해 합니다. 미용 관리가 필요하지만 털이 곱슬곱슬해 사랑스러운 매력이 있습니다. 예방접종 완료, 중성화는 아직 진행하지 않았습니다. 평생 책임져 주실 가족을 기다립니다.',
 '2025-09-02', NULL, '010-2222-3333', 'DOG', '푸들', 1, '갈색', 'MALE', 5.00, 'PROTECTING', 'Y', 'N', 215, FALSE, FALSE, 2, NULL, 2),

('순한 믹스견, 따뜻한 집이 필요해요',
 '약 3살 정도로 추정되는 믹스견입니다. 시골 마을에서 떠돌던 아이를 구조해 보호하고 있습니다. 낯가림은 조금 있으나 금세 마음을 열고 꼬리를 흔듭니다. 사람 손길을 좋아하며, 다른 강아지와도 잘 지냅니다. 현재는 건강검진을 마쳤고, 예방접종도 완료되었습니다. 이미 입양 의사가 있어 입양 절차 진행 중입니다.',
 '2025-09-03', '2025-09-06', '010-3333-4444', 'DOG', '믹스', 3, '흰색', 'FEMALE', 8.50, 'ADOPTED', 'Y', 'Y', 178, FALSE, FALSE, NULL, 3, 3),

('러블리 러시안블루 고양이 분양',
 '4살 된 러시안블루 수컷입니다. 집사와 함께 살다가 사정상 입양을 보내게 되었습니다. 차분하고 얌전한 성격으로, 하루 종일 창밖을 바라보는 걸 좋아합니다. 사람 품에 안기는 것도 좋아해 가족이 되면 큰 위로가 될 아이입니다. 현재까지 큰 질병 이력 없으며, 예방접종과 중성화 완료했습니다.',
 '2025-09-04', NULL, '010-4444-5555', 'CAT', '러시안블루', 4, '회색', 'MALE', 4.10, 'PROTECTING', 'Y', 'Y', 89, FALSE, FALSE, NULL, 4, 4),

('애교 많은 포메라니안, 활발한 에너지 가득!',
 '2살 된 포메라니안으로, 주인을 잃고 보호소로 들어왔습니다. 활발하고 장난기가 많아 매일 뛰어다니며 에너지를 발산합니다. 사람과 함께 있을 때 가장 행복해하고, 간식을 아주 좋아합니다. 털 빠짐이 다소 있으니 꾸준한 빗질이 필요합니다. 건강검진 결과 양호하며, 예방접종과 중성화 모두 완료했습니다.',
 '2025-09-05', '2025-09-07', '010-5555-6666', 'DOG', '포메라니안', 2, '베이지', 'FEMALE', 2.80, 'PROTECTING', 'Y', 'Y', 132, FALSE, FALSE, 5, NULL, 5),

('길에서 구조된 아기 고양이',
 '1살 정도로 추정되는 수컷 코숏입니다. 길에서 홀로 있던 것을 발견해 구조했습니다. 사람을 무서워하지 않고 금방 다가와 안기려 합니다. 기본 접종 완료했고, 중성화는 아직 진행하지 않았습니다. 기침이나 눈물 증상 없이 건강하며, 모래 사용도 잘합니다. 좋은 집에서 평생 사랑받으며 자라기를 바랍니다.',
 '2025-09-06', NULL, '010-6666-7777', 'CAT', '코숏', 1, '흰색/검정', 'MALE', 3.00, 'ADOPTED', 'Y', 'N', 220, FALSE, FALSE, 6, NULL, 6),

('생후 6개월 믹스견 아가, 함께 자라주실 가족 찾습니다',
 '아직 어린 믹스견 아가입니다. 보호소에 들어온 지 한 달 되었으며, 현재 매우 활발하고 건강합니다. 장난감을 가지고 놀기를 좋아하며, 다른 강아지들과도 잘 지냅니다. 예방접종 일부 진행했고, 중성화는 하지 않았습니다. 기초 훈련이 필요하지만 금세 배울 똑똑한 아이입니다.',
 '2025-09-07', NULL, '010-7777-8888', 'DOG', '믹스', 0, '검정/갈색', 'FEMALE', 6.00, 'PROTECTING', 'N', 'N', 98, FALSE, FALSE, NULL, 7, 7),

('페르시안 장모 고양이, 미모의 여왕님',
 '5살 된 페르시안 암컷 고양이입니다. 오랜 기간 보호소에서 지내던 아이로, 차분하면서도 품위 있는 성격입니다. 털이 길어 관리가 필요하지만, 빗질할 때 얌전히 있어주는 편입니다. 사람과 교감하는 걸 좋아해 새 가족에게도 금방 적응할 것입니다. 접종, 중성화 완료 상태입니다.',
 '2025-09-08', '2025-09-09', '010-8888-9999', 'CAT', '페르시안', 5, '흰색', 'FEMALE', 4.50, 'PROTECTING', 'Y', 'Y', 76, FALSE, FALSE, NULL, 8, 8),

('순둥이 말티즈 남아, 평생 가족 구합니다',
 '2살 된 말티즈 수컷입니다. 버려져서 구조된 아이지만, 여전히 사람을 좋아하고 믿습니다. 순하고 활발한 성격으로, 산책 시 다른 강아지와도 잘 지냅니다. 예방접종, 중성화 모두 완료했으며, 기초 건강검진에서도 이상이 없었습니다. 작고 소중한 생명을 평생 책임져 주실 분을 찾습니다.',
 '2025-09-09', NULL, '010-9999-0000', 'DOG', '말티즈', 2, '흰색', 'MALE', 3.80, 'PROTECTING', 'Y', 'Y', 154, FALSE, FALSE, 9, NULL, 9),

('형제 코숏 아기 고양이 두 마리',
 '생후 3개월 정도 된 아기 고양이 두 마리를 함께 입양 보내려 합니다. 길에서 구조된 아이들로, 서로에게 의지하며 자랍니다. 아직 작아서 예방접종은 진행 중이고, 중성화는 하지 않았습니다. 활발하고 호기심이 많으며, 장난감 놀이를 무척 좋아합니다. 두 아이를 함께 입양해 주실 분을 찾습니다.',
 '2025-09-10', '2025-09-11', '010-1010-2020', 'CAT', '코숏', 0, '검정/흰색', 'FEMALE', 1.20, 'PROTECTING', 'N', 'N', 61, FALSE, FALSE, NULL, 10, 10);


-- 입양 게시판 좋아요 (adoptionPostLiked) 10개
INSERT INTO adoptionPostLiked (post_id, user_id, head_id) VALUES
(1, 2, NULL),
(1, 3, NULL),
(2, 4, NULL),
(3, 1, NULL),
(4, 5, NULL),
(5, 6, NULL),
(6, 7, NULL),
(7, 8, NULL),
(8, 9, NULL),
(9, 10, NULL);

-- 입양 게시판 신고 (adoptionPostReport) 10개
INSERT INTO adoptionPostReport (category, etc_detail, created_at, status, post_id, user_id, head_id) VALUES
('SPAM', NULL, '2025-09-01', FALSE, 1, 2, NULL),
('ABUSE', '동물 학대 의심', '2025-09-02', FALSE, 2, 3, NULL),
('PORN', NULL, '2025-09-03', TRUE, 3, 4, NULL),
('FRAUD', '입양비 관련 사기 의심', '2025-09-04', FALSE, 4, 5, NULL),
('MISINFO', '정보가 잘못됨', '2025-09-05', FALSE, 5, 6, NULL),
('ETC', '기타 사유', '2025-09-06', FALSE, 6, 7, NULL),
('SPAM', NULL, '2025-09-07', FALSE, 7, 8, NULL),
('ABUSE', '학대 가능성 있음', '2025-09-08', TRUE, 8, 9, NULL),
('MISINFO', '동물 정보 불일치', '2025-09-09', FALSE, 9, 10, NULL),
('FRAUD', '후원금 관련 문제', '2025-09-10', FALSE, 10, 1, NULL);

-- 입양 게시판 댓글 (adoptionPostComment) 10개
INSERT INTO adoptionPostComment (content, created_at, updated_at, is_blinded, is_deleted, post_id, user_id, head_id) VALUES
('너무 귀엽네요! 입양 원합니다.', '2025-09-01', NULL, FALSE, FALSE, 1, 2, NULL),
('성격은 어떤가요?', '2025-09-02', NULL, FALSE, FALSE, 2, 3, NULL),
('건강상태 괜찮나요?', '2025-09-03', NULL, FALSE, FALSE, 3, 4, NULL),
('사진 더 볼 수 있을까요?', '2025-09-04', NULL, FALSE, FALSE, 4, 5, NULL),
('접종은 완료됐나요?', '2025-09-05', NULL, FALSE, FALSE, 5, 6, NULL),
('입양 조건 알려주세요.', '2025-09-06', NULL, FALSE, FALSE, 6, 7, NULL),
('중성화 했나요?', '2025-09-07', NULL, FALSE, FALSE, 7, 8, NULL),
('아이들이 너무 예뻐요!', '2025-09-08', NULL, FALSE, FALSE, 8, 9, NULL),
('입양 절차가 궁금합니다.', '2025-09-09', NULL, FALSE, FALSE, 9, 10, NULL),
('제가 돌볼 수 있을 것 같아요.', '2025-09-10', NULL, FALSE, FALSE, 10, 1, NULL);

-- 입양 게시판 댓글 신고 (adoptionPostCommentReport) 10개
INSERT INTO adoptionPostCommentReport (category, etc_detail, created_at, status, comment_id, user_id, head_id) VALUES
('SPAM', NULL, '2025-09-01', FALSE, 1, 3, NULL),
('ABUSE', '부적절한 댓글', '2025-09-02', FALSE, 2, 4, NULL),
('PORN', NULL, '2025-09-03', TRUE, 3, 5, NULL),
('FRAUD', '허위 댓글 의심', '2025-09-04', FALSE, 4, 6, NULL),
('MISINFO', '정보 오류 있음', '2025-09-05', FALSE, 5, 7, NULL),
('ETC', '기타 사유', '2025-09-06', FALSE, 6, 8, NULL),
('SPAM', NULL, '2025-09-07', FALSE, 7, 9, NULL),
('ABUSE', '공격적 표현 포함', '2025-09-08', TRUE, 8, 10, NULL),
('MISINFO', '사실과 다름', '2025-09-09', FALSE, 9, 1, NULL),
('FRAUD', '기부 관련 허위 주장', '2025-09-10', FALSE, 10, 2, NULL);

-- 입양 게시판 파일 (adoptionPostFiles) 10개
INSERT INTO adoptionPostFiles (file_rename, file_path, uploaded_at, post_id) VALUES
('cat1.jpg', '/uploads/adoption/cat1.jpg', '2025-09-01', 1),
('dog1.jpg', '/uploads/adoption/dog1.jpg', '2025-09-02', 2),
('dog2.jpg', '/uploads/adoption/dog2.jpg', '2025-09-03', 3),
('cat2.jpg', '/uploads/adoption/cat2.jpg', '2025-09-04', 4),
('dog3.jpg', '/uploads/adoption/dog3.jpg', '2025-09-05', 5),
('cat3.jpg', '/uploads/adoption/cat3.jpg', '2025-09-06', 6),
('dog4.jpg', '/uploads/adoption/dog4.jpg', '2025-09-07', 7),
('cat4.jpg', '/uploads/adoption/cat4.jpg', '2025-09-08', 8),
('dog5.jpg', '/uploads/adoption/dog5.jpg', '2025-09-09', 9),
('cat5.jpg', '/uploads/adoption/cat5.jpg', '2025-09-10', 10); 