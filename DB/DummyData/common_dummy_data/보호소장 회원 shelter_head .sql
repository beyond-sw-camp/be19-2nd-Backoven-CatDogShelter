-- 보호소장 회원 (shelter_head) 10개

INSERT INTO shelter_head 
(head_account, head_password, e_mail, ceo_name, ceo_name2, answer, company_name, biz_number, cor_number, company_address, open_date, close_date, sigungu_id, id2) 
VALUES
-- 서울: 대표자 2명
('seoulsarang', 'Shelter2025!', 'seoul@example.com', '김영희', '박철수', '치킨', '서울사랑보호소', '111-22-33344', '990001-1234567', '서울 종로구 청운효자동 10', '2018-05-01', NULL, 1, 1),

-- 부산: 대표자 1명
('busanhappy', 'HappyBusan#1', 'busan@example.com', '박정식', NULL, '불고기', '부산해피보호소', '222-33-44455', '880002-2345678', '부산 중구 중앙대로 50', '2019-03-15', NULL, 2, 2),

-- 대구: 대표자 2명
('daegupet', 'PetCareDg9', 'daegu@example.com', '이민호', '정하늘', '파랑', '대구펫케어센터', '333-44-55566', '770003-3456789', '대구 중구 중앙로 20', '2020-07-01', NULL, 3, 3),

-- 인천: 대표자 1명
('incheonhope', 'HopeCenter1', 'incheon@example.com', '정미경', NULL, '민수', '인천희망보호소', '444-55-66677', '660004-4567890', '인천 남구 주안로 100', '2017-11-11', NULL, 4, 4),

-- 광주: 대표자 2명
('gwangjupet', 'Pet&CareGJ', 'gwangju@example.com', '한경숙', '서동우', '대전', '광주펫앤케어센터', '555-66-77788', '550005-5678901', '광주 동구 금남로 12', '2016-04-20', NULL, 5, 5),

-- 대전: 대표자 1명
('daejeonpaws', 'PawsDaejeon2', 'daejeon@example.com', '최영수', NULL, '아버지', '대전포우즈보호소', '666-77-88899', '440006-6789012', '대전 동구 대전로 33', '2019-09-30', NULL, 6, 6),

-- 울산: 대표자 1명
('ulsangoodpet', 'GoodPetUS', 'ulsan@example.com', '이우람', NULL, '일본', '울산굿펫센터', '777-88-99900', '330007-7890123', '울산 중구 번영로 8', '2021-02-10', NULL, 7, 7),

-- 경기: 대표자 2명
('ggshelter', 'ShelterGG8', 'gg@example.com', '박준성', '이수현', '박선생님', '경기동물쉼터', '888-99-00011', '220008-8901234', '수원시 장안구 고색동 45', '2020-12-01', NULL, 8, 8),

-- 강원: 대표자 1명
('gwanlove', 'LoveAnimalsGW', 'gw@example.com', '송현우', NULL, '초코', '강원러브센터', '999-00-11122', '110009-9012345', '춘천시 중앙로 77', '2018-08-15', NULL, 9, 9),

-- 충북: 대표자 2명
('chungbucare', 'CareCB5', 'cb@example.com', '류민지', '김태형', '해운대초등학교', '충북케어보호소', '101-12-21345', '660010-0123456', '청주시 상당구 흥덕로 22', '2017-06-06', NULL, 10, 10);