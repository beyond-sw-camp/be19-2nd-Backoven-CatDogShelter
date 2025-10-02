use CatDogShelter;
-- 기존 테이블 삭제

SET FOREIGN_KEY_CHECKS = 0;
-- 1. 파일 테이블들
DROP TABLE IF EXISTS noticeFiles;
DROP TABLE IF EXISTS donationPostFiles;
DROP TABLE IF EXISTS postFiles;
DROP TABLE IF EXISTS missingPostFiles;
DROP TABLE IF EXISTS sightingPostFiles;
DROP TABLE IF EXISTS adoptionPostFiles;
DROP TABLE IF EXISTS volunteerPostFiles;

-- 2. 신고 테이블들
DROP TABLE IF EXISTS donationPostCommentReport;
DROP TABLE IF EXISTS postCommentReport;
DROP TABLE IF EXISTS postReport;
DROP TABLE IF EXISTS missingPostCommentReport;
DROP TABLE IF EXISTS missingPostReport;
DROP TABLE IF EXISTS sightingPostCommentReport;
DROP TABLE IF EXISTS sightingPostReport;
DROP TABLE IF EXISTS adoptionPostCommentReport;
DROP TABLE IF EXISTS adoptionPostReport;
DROP TABLE IF EXISTS volunteerPostCommentReport;
DROP TABLE IF EXISTS volunteerPostReport;

-- 3. 댓글 테이블들
DROP TABLE IF EXISTS donationPostComment;
DROP TABLE IF EXISTS postComment;
DROP TABLE IF EXISTS missingPostComment;
DROP TABLE IF EXISTS sightingPostComment;
DROP TABLE IF EXISTS adoptionPostComment;
DROP TABLE IF EXISTS volunteerPostComment;

-- 4. 좋아요 테이블들
DROP TABLE IF EXISTS noticeLiked;
DROP TABLE IF EXISTS donationPostLiked;
DROP TABLE IF EXISTS postLiked;
DROP TABLE IF EXISTS missingPostLiked;
DROP TABLE IF EXISTS sightingPostLiked;
DROP TABLE IF EXISTS adoptionPostLiked;
DROP TABLE IF EXISTS volunteerPostLiked;

-- 5. 게시판 본문
DROP TABLE IF EXISTS notice;
DROP TABLE IF EXISTS donationPost;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS missingPost;
DROP TABLE IF EXISTS sightingPost;
DROP TABLE IF EXISTS adoptionPost;
DROP TABLE IF EXISTS volunteerPost;

-- 6. 상위 엔터티 (모임/메세지 등)
DROP TABLE IF EXISTS volunteerAssociationApplicationDetails;
DROP TABLE IF EXISTS volunteerAssociation;
DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS loginHistory;
DROP TABLE IF EXISTS questionCategory;


-- 7. 사용자 / 보호소 / 봉사왕
DROP TABLE IF EXISTS shelter_head;
DROP TABLE IF EXISTS volunteerKing;
DROP TABLE IF EXISTS user;

-- 8. 코드 테이블
DROP TABLE IF EXISTS rating;
DROP TABLE IF EXISTS sigungu;
DROP TABLE IF EXISTS sido;
SET FOREIGN_KEY_CHECKS = 1;

-- =========================
-- 지역
-- =========================
-- 시/도 코드 --
CREATE TABLE sido (
    sido_id INT NOT NULL AUTO_INCREMENT,
    sido_code VARCHAR(20) NOT NULL,
    sido_name VARCHAR(20) NOT NULL,
    PRIMARY KEY (sido_id)
);

-- 시/군/구 코드 --
CREATE TABLE sigungu (
    sigungu_id INT NOT NULL AUTO_INCREMENT,
    sigungu_code VARCHAR(20),
    sigungu_name VARCHAR(20),
    sido_id INT NOT NULL,
    PRIMARY KEY (sigungu_id),
    CONSTRAINT fk_sigungu_sido FOREIGN KEY (sido_id) REFERENCES sido(sido_id)
);

-- =========================
-- 등급 / 질문 / 회원 / 보호소장
-- =========================
-- 등급 --
CREATE TABLE rating (
    id INT NOT NULL,
    name VARCHAR(10) NOT NULL,
    standard INT NOT NULL,
    PRIMARY KEY (id)
);
-- 질문 카테고리 --
CREATE TABLE questionCategory (
    id INT NOT NULL AUTO_INCREMENT,
    question TEXT NOT NULL,
    PRIMARY KEY (id)
);

-- 일반회원 --
CREATE TABLE user (
    user_id INT NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(20) NOT NULL,          -- 회원명
    user_account VARCHAR(20) NOT NULL UNIQUE,-- 아이디
    user_password VARCHAR(255) NOT NULL,     -- 비밀번호
    e_mail VARCHAR(50) NOT NULL UNIQUE,      -- 이메일
    detail_address VARCHAR(255) NULL,        -- 상세주소
    answer VARCHAR(255) NOT NULL,            -- 지정답변
    user_phone VARCHAR(20) NOT NULL UNIQUE,  -- 전화번호
    cumulative_volunteer_time INT NOT NULL,  -- 누적봉사시간
    month_volunteer_time INT NULL,           -- 월봉사시간
    volunteer_count INT NOT NULL,            -- 봉사횟수
    deactivation_times INT NOT NULL,         -- 블라인드 횟수
    user_status ENUM('GENERAL','BLACK','CANCEL') NOT NULL, -- 회원상태
    activation_date VARCHAR(20) NULL,    -- 정지 해제일
    rating_id INT NOT NULL default 0,                  -- 회원등급 FK
    sigungu_id INT NOT NULL,                 -- 소속 시군구 FK
    questionCategory_id INT NOT NULL,        -- 지정질문카테고리번호 FK
    PRIMARY KEY (user_id),
    CONSTRAINT fk_user_rating FOREIGN KEY (rating_id) REFERENCES rating(id),
    CONSTRAINT fk_user_sigungu FOREIGN KEY (sigungu_id) REFERENCES sigungu(sigungu_id),
    CONSTRAINT fk_user_question FOREIGN KEY (questionCategory_id) REFERENCES questionCategory(id)
);

-- 봉사왕 --
CREATE TABLE volunteerKing (
    id INT NOT NULL AUTO_INCREMENT,
    category BOOLEAN NOT NULL DEFAULT FALSE,
    year INT,
    month INT,
    volunteer_time INT,
    user_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_vking_user FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- 보호소장 회원 --
CREATE TABLE shelter_head (
    head_id INT NOT NULL AUTO_INCREMENT,
    ceo_name VARCHAR(20) NOT NULL,           -- 대표자 성명
    ceo_name2 VARCHAR(20) NULL,              -- 대표자 성명2
    head_account VARCHAR(20) NOT NULL UNIQUE,-- 아이디
    head_password VARCHAR(255) NOT NULL,     -- 비밀번호
    e_mail VARCHAR(50) NOT NULL UNIQUE,      -- 이메일
    answer VARCHAR(255) NOT NULL,            -- 지정답변
    head_phone VARCHAR(20) NOT NULL UNIQUE,  -- 전화번호
    company_name VARCHAR(255) NOT NULL,      -- 상호명
    biz_number VARCHAR(20) NOT NULL,         -- 사업자등록번호
    cor_number VARCHAR(20) NOT NULL,         -- 법인등록번호
    company_address VARCHAR(255) NOT NULL,   -- 사업장주소
    open_date VARCHAR(20) NOT NULL,          -- 개업일자
    close_date VARCHAR(20) NULL,             -- 폐업일자 (nullable)
    sigungu_id INT NOT NULL,                 -- 소속 시군구 FK
    questionCategory_id INT NOT NULL,        -- 지정질문카테고리번호 FK
    PRIMARY KEY (head_id),
    CONSTRAINT fk_head_sigungu FOREIGN KEY (sigungu_id) REFERENCES sigungu(sigungu_id),
    CONSTRAINT fk_head_question FOREIGN KEY (questionCategory_id) REFERENCES questionCategory(id)
);
-- =========================
-- 로그인 / 메세지
-- =========================
-- 로그인 이력 --
CREATE TABLE loginHistory (
    id INT NOT NULL AUTO_INCREMENT,
    ip_address VARCHAR(39) NOT NULL,
    logged_at VARCHAR(30) NOT NULL,
    user_id INT,
    head_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_login_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_login_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 메세지 --
CREATE TABLE message (
    id INT NOT NULL AUTO_INCREMENT,
    content TEXT NOT NULL,
    created_at VARCHAR(20) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT FALSE,
    subject_number INT NOT NULL,
    subject_user_id INT,
    send_user_id INT,
    subject_head_id INT,
    send_head_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_msg_subject_user FOREIGN KEY (subject_user_id) REFERENCES user(user_id),
    CONSTRAINT fk_msg_send_user FOREIGN KEY (send_user_id) REFERENCES user(user_id),
    CONSTRAINT fk_msg_subject_head FOREIGN KEY (subject_head_id) REFERENCES shelter_head(head_id),
    CONSTRAINT fk_msg_send_head FOREIGN KEY (send_head_id) REFERENCES shelter_head(head_id)
);


-- =========================
-- 봉사모임(봉사활동 공고)
-- =========================
-- 봉사모임(봉사활동 공고) --
CREATE TABLE volunteerAssociation (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    content TEXT,
    created_at VARCHAR(20) NOT NULL,
    time INT NOT NULL,
    start_date VARCHAR(20) NOT NULL,
    detail_address VARCHAR(255),
    deadline BOOLEAN NOT NULL DEFAULT FALSE,
    number_of_people INT,
    is_end BOOLEAN NOT NULL DEFAULT FALSE,
    head_id INT NOT NULL,
    sigungu_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_va_user FOREIGN KEY (head_id) REFERENCES shelter_head(head_id),
    CONSTRAINT fk_va_sigungu FOREIGN KEY (sigungu_id) REFERENCES sigungu(sigungu_id)
);

-- 봉사모임 신청내역 --
CREATE TABLE volunteerAssociationApplicationDetails (
    id INT NOT NULL AUTO_INCREMENT,
    status BOOLEAN NOT NULL DEFAULT FALSE,
    time INT DEFAULT 0,
    volunteer_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_vaad_volunteer FOREIGN KEY (volunteer_id) REFERENCES volunteerassociation(id),
    CONSTRAINT fk_vaad_user FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- 봉사후기 게시판 --
CREATE TABLE volunteerPost (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    created_at VARCHAR(20) NOT NULL,
    updated_at VARCHAR(20),
    view INT NOT NULL DEFAULT 0,
    is_blinded BOOLEAN NOT NULL DEFAULT FALSE,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    volappdetail_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_vpost_volappdetail FOREIGN KEY (volappdetail_id) REFERENCES volunteerAssociationApplicationDetails(id)
);

-- 봉사후기 게시판 좋아요 --
CREATE TABLE volunteerPostLiked (
    id INT NOT NULL AUTO_INCREMENT, -- PK
    post_id INT NOT NULL,           -- FK
    head_id INT,                    -- FK (nullable)
    user_id INT,                    -- FK (nullable)
    PRIMARY KEY (id),
    CONSTRAINT fk_vpl_post FOREIGN KEY (post_id) REFERENCES volunteerPost(id),
    CONSTRAINT fk_vpl_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_vpl_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 봉사후기 게시판 신고 --
CREATE TABLE volunteerPostReport (
    id INT NOT NULL AUTO_INCREMENT,
    category ENUM('SPAM','ABUSE','PORN','FRAUD','MISINFO','ETC') NOT NULL,
    etc_detail TEXT,
    created_at VARCHAR(20) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT FALSE,
    post_id INT NOT NULL,
    user_id INT,
    head_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_vreport_post FOREIGN KEY (post_id) REFERENCES volunteerPost(id),
    CONSTRAINT fk_vreport_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_vreport_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 봉사후기 댓글 --
CREATE TABLE volunteerPostComment (
    id INT NOT NULL AUTO_INCREMENT,
    content TEXT,
    created_at VARCHAR(20) NOT NULL,
    updated_at VARCHAR(20),
    is_blinded BOOLEAN NOT NULL DEFAULT FALSE,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    head_id INT,
    user_id INT,
    post_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_vcomment_post FOREIGN KEY (post_id) REFERENCES volunteerPost(id),
    CONSTRAINT fk_vcomment_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_vcomment_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 봉사후기 댓글 신고 -- 
CREATE TABLE volunteerPostCommentReport (
    id INT NOT NULL AUTO_INCREMENT,
    category ENUM('SPAM','ABUSE','PORN','FRAUD','MISINFO','ETC') NOT NULL,
    etc_detail TEXT,
    created_at VARCHAR(20) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT FALSE,
    head_id INT,
    user_id INT,
    comment_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_vpcr_comment FOREIGN KEY (comment_id) REFERENCES volunteerPostComment(id),
    CONSTRAINT fk_vpcr_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_vpcr_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 봉사모임 업로드 파일 --
CREATE TABLE volunteerPostFiles (
    id INT NOT NULL AUTO_INCREMENT,
    file_rename VARCHAR(100) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    uploaded_at VARCHAR(20) NOT NULL,
    post_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_vfiles_post FOREIGN KEY (post_id) REFERENCES volunteerPost(id)
);


-- =========================
-- 입양 게시판
-- =========================
-- 입양 게시판 --
CREATE TABLE adoptionPost (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    created_at VARCHAR(20) NOT NULL,
    updated_at VARCHAR(20),
    user_phone VARCHAR(20) NOT NULL,
    animal_type ENUM('CAT','DOG') NOT NULL,
    breed VARCHAR(20) NOT NULL,
    age INT NOT NULL,
    color VARCHAR(20) NOT NULL,
    gender ENUM('MALE','FEMALE') NOT NULL,
    weight DECIMAL(4,2) NOT NULL,
    status ENUM('PROTECTING','ADOPTED') NOT NULL,
    vaccination ENUM('Y','N') NOT NULL,
    neutering ENUM('Y','N') NOT NULL,
    view INT NOT NULL DEFAULT 0,
    is_blind BOOLEAN NOT NULL DEFAULT FALSE,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    user_id INT,
    head_id INT,
    sigungu_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_ap_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_ap_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id),
    CONSTRAINT fk_ap_sigungu FOREIGN KEY (sigungu_id) REFERENCES sigungu(sigungu_id)
);

-- 입양 게시판 추천 -- 
CREATE TABLE adoptionPostLiked (
    id INT NOT NULL AUTO_INCREMENT,  
    post_id INT NOT NULL,            
    user_id INT,                    
    head_id INT,                        
    PRIMARY KEY (id),
    CONSTRAINT fk_apl_post FOREIGN KEY (post_id) REFERENCES adoptionPost(id),
    CONSTRAINT fk_apl_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_apl_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id),
    -- 중복 방지: 회원이 누른 경우
    UNIQUE (post_id, user_id),
    -- 중복 방지: 보호소장이 누른 경우
    UNIQUE (post_id, head_id)
);
-- 입양 게시판 신고 --
CREATE TABLE adoptionPostReport (
    id INT NOT NULL AUTO_INCREMENT,
    category ENUM('SPAM','ABUSE','PORN','FRAUD','MISINFO','ETC') NOT NULL,
    etc_detail TEXT,
    created_at VARCHAR(20) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT FALSE,
    post_id INT NOT NULL,
    user_id INT,
    head_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_apr_post FOREIGN KEY (post_id) REFERENCES adoptionPost(id),
    CONSTRAINT fk_apr_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_apr_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 입양 댓글 -
CREATE TABLE adoptionPostComment (
    id INT NOT NULL AUTO_INCREMENT,
    content TEXT NOT NULL,
    created_at VARCHAR(20) NOT NULL,
    updated_at VARCHAR(20),
    is_blinded BOOLEAN NOT NULL DEFAULT FALSE,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    post_id INT NOT NULL,
    user_id INT,
    head_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_apc_post FOREIGN KEY (post_id) REFERENCES adoptionPost(id),
    CONSTRAINT fk_apc_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_apc_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 입양 댓글 신고 --
CREATE TABLE adoptionPostCommentReport (
    id INT NOT NULL AUTO_INCREMENT,
    category ENUM('SPAM','ABUSE','PORN','FRAUD','MISINFO','ETC') NOT NULL,
    etc_detail TEXT,
    created_at VARCHAR(20) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT FALSE,
    comment_id INT NOT NULL,
    user_id INT,
    head_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_apcr_comment FOREIGN KEY (comment_id) REFERENCES adoptionPostComment(id),
    CONSTRAINT fk_apcr_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_apcr_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 입양 업로드 파일 --
CREATE TABLE adoptionPostFiles (
    id INT NOT NULL AUTO_INCREMENT,
    file_rename VARCHAR(100) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    uploaded_at VARCHAR(20) NOT NULL,
    post_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_apf_post FOREIGN KEY (post_id) REFERENCES adoptionPost(id)
);

-- =========================
-- 목격제보 게시판
-- =========================
-- 목격제보 게시판 --
CREATE TABLE sightingPost (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    created_at VARCHAR(20) NOT NULL,
    updated_at VARCHAR(20),
    sighted_at VARCHAR(20) NOT NULL,
    sighted_place VARCHAR(255) NOT NULL,
    color VARCHAR(20) NOT NULL,
    animal_type ENUM('CAT','DOG') NOT NULL,
    breed VARCHAR(20) NOT NULL,
    view INT NOT NULL DEFAULT 0,
    is_blinded BOOLEAN NOT NULL DEFAULT FALSE,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    user_id INT,
    head_id INT,
    sigungu_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_sp_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_sp_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id),
    CONSTRAINT fk_sp_sigungu FOREIGN KEY (sigungu_id) REFERENCES sigungu(sigungu_id)
);

-- 목격제보 게시판 추천 --
CREATE TABLE sightingPostLiked (
    id INT NOT NULL AUTO_INCREMENT,  
    post_id INT NOT NULL,            
    user_id INT,                     
    head_id INT,                     
    PRIMARY KEY (id),
    CONSTRAINT fk_spl_post FOREIGN KEY (post_id) REFERENCES sightingPost(id),
    CONSTRAINT fk_spl_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_spl_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 목격제보 게시판 신고 --
CREATE TABLE sightingPostReport (
    id INT NOT NULL AUTO_INCREMENT,
    category ENUM('SPAM','ABUSE','PORN','FRAUD','MISINFO','ETC') NOT NULL,
    etc_detail TEXT,
    created_at VARCHAR(20) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT FALSE,
    post_id INT NOT NULL,
    user_id INT,
    head_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_spr_post FOREIGN KEY (post_id) REFERENCES sightingPost(id),
    CONSTRAINT fk_spr_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_spr_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 목격제보 댓글 --
CREATE TABLE sightingPostComment (
    id INT NOT NULL AUTO_INCREMENT,
    content TEXT NOT NULL,
    created_at VARCHAR(20) NOT NULL,
    updated_at VARCHAR(20),
    is_blinded BOOLEAN NOT NULL DEFAULT FALSE,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    post_id INT NOT NULL,
    user_id INT,
    head_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_spc_post FOREIGN KEY (post_id) REFERENCES sightingPost(id),
    CONSTRAINT fk_spc_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_spc_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 목격제보 댓글 신고 --
CREATE TABLE sightingPostCommentReport (
    id INT NOT NULL AUTO_INCREMENT,
    category ENUM('SPAM','ABUSE','PORN','FRAUD','MISINFO','ETC') NOT NULL,
    etc_detail TEXT,
    created_at VARCHAR(20) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT FALSE,
    comment_id INT NOT NULL,
    user_id INT,
    head_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_spcr_comment FOREIGN KEY (comment_id) REFERENCES sightingPostComment(id),
    CONSTRAINT fk_spcr_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_spcr_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 목격제보 파일 업로드 --
CREATE TABLE sightingPostFiles (
    id INT NOT NULL AUTO_INCREMENT,
    file_rename VARCHAR(100) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    uploaded_at VARCHAR(20) NOT NULL,
    post_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_spf_post FOREIGN KEY (post_id) REFERENCES sightingPost(id)
);

-- =========================
-- 실종신고 게시판
-- =========================
-- 실종신고 게시판 --
CREATE TABLE missingPost (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    created_at VARCHAR(20) NOT NULL,
    updated_at VARCHAR(20),
    is_blinded BOOLEAN NOT NULL DEFAULT FALSE,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    status BOOLEAN NOT NULL DEFAULT FALSE,
    detail_address VARCHAR(50),
    gender ENUM('MALE','FEMALE') NOT NULL,
    age INT,
    registration_num INT,
    breed VARCHAR(20),
    animal_type ENUM('CAT','DOG') NOT NULL,
    color VARCHAR(20),
    weight DECIMAL(4,2),
    lost_date VARCHAR(20) NOT NULL,
    feature TEXT,
    contact VARCHAR(20),
    view INT NOT NULL DEFAULT 0,
    user_id INT,
    head_id INT,
    sigungu_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_mp_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_mp_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id),
    CONSTRAINT fk_mp_sigungu FOREIGN KEY (sigungu_id) REFERENCES sigungu(sigungu_id)
);

-- 실종신고 게시판 신고 --
CREATE TABLE missingPostReport (
    id INT NOT NULL AUTO_INCREMENT,
    category ENUM('SPAM','ABUSE','PORN','FRAUD','MISINFO','ETC') NOT NULL,
    etc_detail TEXT,
    created_at VARCHAR(20) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT FALSE,
    post_id INT NOT NULL,
    user_id INT,
    head_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_mpr_post FOREIGN KEY (post_id) REFERENCES missingPost(id),
    CONSTRAINT fk_mpr_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_mpr_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 실종신고 게시판 추천 --
CREATE TABLE missingPostLiked (
    id INT NOT NULL AUTO_INCREMENT,  
    post_id INT NOT NULL,                
    user_id INT,                     
    head_id INT,                     
    PRIMARY KEY (id),
    CONSTRAINT fk_mpl_post FOREIGN KEY (post_id) REFERENCES missingPost(id),
    CONSTRAINT fk_mpl_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_mpl_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);


-- 실종신고 댓글 --
CREATE TABLE missingPostComment (
    id INT NOT NULL AUTO_INCREMENT,
    content TEXT NOT NULL,
    created_at VARCHAR(20) NOT NULL,
    updated_at VARCHAR(20),
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    is_blinded BOOLEAN NOT NULL DEFAULT FALSE,
    post_id INT NOT NULL,
    user_id INT,
    head_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_mpc_post FOREIGN KEY (post_id) REFERENCES missingPost(id),
    CONSTRAINT fk_mpc_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_mpc_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 실종신고 댓글 신고 --
CREATE TABLE missingPostCommentReport (
    id INT NOT NULL AUTO_INCREMENT,
    category ENUM('SPAM','ABUSE','PORN','FRAUD','MISINFO','ETC') NOT NULL,
    etc_detail TEXT,
    created_at VARCHAR(20) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT FALSE,
    comment_id INT NOT NULL,
    user_id INT,
    head_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_mpcr_comment FOREIGN KEY (comment_id) REFERENCES missingPostComment(id),
    CONSTRAINT fk_mpcr_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_mpcr_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);


-- 실종신고 파일 업로드 --
CREATE TABLE missingPostFiles (
    id INT NOT NULL AUTO_INCREMENT,
    file_rename VARCHAR(100) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    uploaded_at VARCHAR(20) NOT NULL,
    post_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_mpf_post FOREIGN KEY (post_id) REFERENCES missingPost(id)
);

-- =========================
-- 자유 게시판
-- =========================
-- 자유게시판 --
CREATE TABLE post (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    created_at VARCHAR(20) NOT NULL,
    updated_at VARCHAR(20),
    view INT NOT NULL DEFAULT 0,
    is_blinded BOOLEAN NOT NULL DEFAULT FALSE,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    user_id INT,
    head_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_post_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_post_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 자유게시판 추천 --
CREATE TABLE postLiked (
    id INT NOT NULL AUTO_INCREMENT,  
    post_id INT NOT NULL,            
    user_id INT,                     
    head_id INT,                     
    PRIMARY KEY (id),
    CONSTRAINT fk_pl_post FOREIGN KEY (post_id) REFERENCES post(id),
    CONSTRAINT fk_pl_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_pl_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 자유게시판 신고 --
CREATE TABLE postReport (
    id INT NOT NULL AUTO_INCREMENT,
    category ENUM('SPAM','ABUSE','PORN','FRAUD','MISINFO','ETC') NOT NULL,
    etc_detail TEXT,
    created_at VARCHAR(20) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT FALSE,
    post_id INT NOT NULL,
    user_id INT,
    head_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_pr_post FOREIGN KEY (post_id) REFERENCES post(id),
    CONSTRAINT fk_pr_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_pr_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 자유게시판 댓글 --
CREATE TABLE postComment (
    id INT NOT NULL AUTO_INCREMENT,
    content TEXT NOT NULL,
    created_at VARCHAR(20) NOT NULL,
    updated_at VARCHAR(20),
    is_blinded BOOLEAN NOT NULL DEFAULT FALSE,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    post_id INT NOT NULL,
    user_id INT,
    head_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_pc_post FOREIGN KEY (post_id) REFERENCES post(id),
    CONSTRAINT fk_pc_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_pc_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 자유게시판 댓글신고 --
CREATE TABLE postCommentReport (
    id INT NOT NULL AUTO_INCREMENT,
    category ENUM('SPAM','ABUSE','PORN','FRAUD','MISINFO','ETC') NOT NULL,
    etc_detail TEXT,
    created_at VARCHAR(20) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT FALSE,
    comment_id INT NOT NULL,
    user_id INT,
    head_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_pcr_comment FOREIGN KEY (comment_id) REFERENCES postComment(id),
    CONSTRAINT fk_pcr_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_pcr_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 자유게시판 업로드 파일 --
CREATE TABLE postFiles (
    id INT NOT NULL AUTO_INCREMENT,
    file_rename VARCHAR(100) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    uploaded_at VARCHAR(20) NOT NULL,
    post_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_pf_post FOREIGN KEY (post_id) REFERENCES post(id)
);

-- =========================
-- 물품기부 게시판
-- =========================
-- 물품기부 게시판 --
CREATE TABLE donationPost (--
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    created_at VARCHAR(20) NOT NULL,
    updated_at VARCHAR(20),
    view INT NOT NULL DEFAULT 0,
    head_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_dp_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 물품기부 게시판 추천 --
CREATE TABLE donationPostLiked (
    id INT NOT NULL AUTO_INCREMENT,  
    post_id INT NOT NULL,            
    user_id INT,                     
    head_id INT,                     
    PRIMARY KEY (id),
    CONSTRAINT fk_dpl_post FOREIGN KEY (post_id) REFERENCES donationPost(id),
    CONSTRAINT fk_dpl_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_dpl_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 물품기부 댓글 --
CREATE TABLE donationPostComment (
    id INT NOT NULL AUTO_INCREMENT,
    content TEXT NOT NULL,
    created_at VARCHAR(20) NOT NULL,
    updated_at VARCHAR(20),
    is_blinded BOOLEAN NOT NULL DEFAULT FALSE,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    post_id INT NOT NULL,
    user_id INT,
    head_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_dpc_post FOREIGN KEY (post_id) REFERENCES donationPost(id),
    CONSTRAINT fk_dpc_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_dpc_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 물품기부 댓글 신고 --
CREATE TABLE donationPostCommentReport (
    id INT NOT NULL AUTO_INCREMENT,
    category ENUM('SPAM','ABUSE','PORN','FRAUD','MISINFO','ETC') NOT NULL,
    etc_detail TEXT,
    created_at VARCHAR(20) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT FALSE,
    comment_id INT NOT NULL,
    user_id INT,
    head_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_dpcr_comment FOREIGN KEY (comment_id) REFERENCES donationPostComment(id),
    CONSTRAINT fk_dpcr_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_dpcr_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 물품기부 업로드 파일 --
CREATE TABLE donationPostFiles (
    id INT NOT NULL AUTO_INCREMENT,
    file_rename VARCHAR(100) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    uploaded_at VARCHAR(20) NOT NULL,
    post_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_dpf_post FOREIGN KEY (post_id) REFERENCES donationPost(id)
);

-- =========================
-- 공지 게시판
-- =========================
-- 공지 게시판 --
CREATE TABLE notice (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    created_at VARCHAR(20) NOT NULL,
    updated_at VARCHAR(20),
    rating_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_notice_rating FOREIGN KEY (rating_id) REFERENCES rating(id)
);

-- 공지 게시판 좋아요 --
CREATE TABLE noticeLiked (
    id INT NOT NULL AUTO_INCREMENT,  
    notice_id INT NOT NULL,          
    user_id INT,                     
    head_id INT,                     
    PRIMARY KEY (id),
    CONSTRAINT fk_nl_notice FOREIGN KEY (notice_id) REFERENCES notice(id),
    CONSTRAINT fk_nl_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_nl_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
);

-- 공지 업로드 파일 --
CREATE TABLE noticeFiles (
    id INT NOT NULL AUTO_INCREMENT,
    file_rename VARCHAR(100) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    uploaded_at VARCHAR(20) NOT NULL,
    notice_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_nf_notice FOREIGN KEY (notice_id) REFERENCES notice(id)
);

-- adoptionPostReport
ALTER TABLE adoptionPostReport
ADD CONSTRAINT uq_adoption_post_report_user UNIQUE (post_id, user_id),
ADD CONSTRAINT uq_adoption_post_report_head UNIQUE (post_id, head_id);

-- missingPostReport
ALTER TABLE missingPostReport
ADD CONSTRAINT uq_missing_post_report_user UNIQUE (post_id, user_id),
ADD CONSTRAINT uq_missing_post_report_head UNIQUE (post_id, head_id);

-- sightingPostReport
ALTER TABLE sightingPostReport
ADD CONSTRAINT uq_sighting_post_report_user UNIQUE (post_id, user_id),
ADD CONSTRAINT uq_sighting_post_report_head UNIQUE (post_id, head_id);

-- volunteerPostReport
ALTER TABLE volunteerPostReport
ADD CONSTRAINT uq_volunteer_post_report_user UNIQUE (post_id, user_id),
ADD CONSTRAINT uq_volunteer_post_report_head UNIQUE (post_id, head_id);

-- postReport
ALTER TABLE postReport
ADD CONSTRAINT uq_post_report_user UNIQUE (post_id, user_id),
ADD CONSTRAINT uq_post_report_head UNIQUE (post_id, head_id);






-- adoptionPostCommentReport
ALTER TABLE adoptionPostCommentReport
ADD CONSTRAINT uq_adoption_comment_report_user UNIQUE (comment_id, user_id),
ADD CONSTRAINT uq_adoption_comment_report_head UNIQUE (comment_id, head_id);

-- donationPostCommentReport
ALTER TABLE donationPostCommentReport
ADD CONSTRAINT uq_donation_comment_report_user UNIQUE (comment_id, user_id),
ADD CONSTRAINT uq_donation_comment_report_head UNIQUE (comment_id, head_id);

-- missingPostCommentReport
ALTER TABLE missingPostCommentReport
ADD CONSTRAINT uq_missing_comment_report_user UNIQUE (comment_id, user_id),
ADD CONSTRAINT uq_missing_comment_report_head UNIQUE (comment_id, head_id);

-- sightingPostCommentReport
ALTER TABLE sightingPostCommentReport
ADD CONSTRAINT uq_sighting_comment_report_user UNIQUE (comment_id, user_id),
ADD CONSTRAINT uq_sighting_comment_report_head UNIQUE (comment_id, head_id);

-- volunteerPostCommentReport
ALTER TABLE volunteerPostCommentReport
ADD CONSTRAINT uq_volunteer_comment_report_user UNIQUE (comment_id, user_id),
ADD CONSTRAINT uq_volunteer_comment_report_head UNIQUE (comment_id, head_id);

-- postCommentReport
ALTER TABLE postCommentReport
ADD CONSTRAINT uq_post_comment_report_user UNIQUE (comment_id, user_id),
ADD CONSTRAINT uq_post_comment_report_head UNIQUE (comment_id, head_id);




use CatDogShelter;
INSERT INTO sido (sido_code, sido_name) VALUES
('11', '서울특별시'),
('26', '부산광역시'),
('27', '대구광역시'),
('28', '인천광역시'),
('29', '광주광역시'),
('30', '대전광역시'),
('31', '울산광역시'),
('41', '경기도'),
('42', '강원도'),
('43', '충청북도');

INSERT INTO sigungu (sigungu_code, sigungu_name, sido_id) VALUES
-- 서울특별시 (sido_id=1)
('11110', '종로구', 1),
('11140', '중구', 1),
('11170', '용산구', 1),
('11200', '성동구', 1),
('11215', '광진구', 1),
('11230', '동대문구', 1),
('11260', '중랑구', 1),
('11290', '성북구', 1),
('11305', '강북구', 1),
('11320', '도봉구', 1),

-- 부산광역시 (sido_id=2)
('26110', '중구', 2),
('26140', '서구', 2),
('26170', '동구', 2),
('26200', '영도구', 2),
('26230', '부산진구', 2),

-- 대구광역시 (sido_id=3)
('27110', '중구', 3),
('27140', '동구', 3),
('27170', '서구', 3),

-- 인천광역시 (sido_id=4)
('28110', '중구', 4),
('28140', '동구', 4),
('28170', '남구', 4),

-- 광주광역시 (sido_id=5)
('29110', '동구', 5),

-- 대전광역시 (sido_id=6)
('30110', '동구', 6),

-- 울산광역시 (sido_id=7)
('31110', '중구', 7),

-- 경기도 (sido_id=8)
('41110', '수원시', 8),
('41111', '수원시 장안구', 8),
('41113', '수원시 권선구', 8),

-- 강원도 (sido_id=9)
('42110', '춘천시', 9),

-- 충청북도 (sido_id=10)
('43110', '청주시', 10);

-- 등급 (rating)
INSERT INTO rating (id, name, standard) VALUES
(-1, '관리자', 0),
(0, '발자국', 0),
(1, '댕냥친구', 10),
(2, '댕냥지킴이', 30),
(3, '댕냥보호천사', 60),
(4, '관리자', 0),
(5, '슈퍼관리자', 0),
(6, '시스템', 0),
(7, '모니터링', 0),
(8, '운영자', 0);

-- 질문 10
INSERT INTO questionCategory (question) VALUES
('어릴 적 별명은 무엇인가요?'),
('첫 반려동물 이름은 무엇인가요?'),
('졸업한 초등학교 이름은 무엇인가요?'),
('좋아하는 음식은 무엇인가요?'),
('좋아하는 색깔은 무엇인가요?'),
('가장 친한 친구의 이름은 무엇인가요?'),
('태어난 고향은 어디인가요?'),
('부모님 성함 중 한 분을 입력해주세요.'),
('첫 해외여행을 간 나라는 어디인가요?'),
('가장 기억에 남는 선생님 성함은 무엇인가요?');

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

-- 봉사왕 (volunteerKing) 10개
INSERT INTO volunteerKing (category, year, month, volunteer_time, user_id) VALUES
(FALSE, NULL, NULL, 150, 4), -- 누적 봉사왕 (user_id=4)
(TRUE, 2025, 9, 30, 2),
(TRUE, 2025, 8, 50, 3),
(TRUE, 2025, 7, 25, 6),
(TRUE, 2025, 9, 60, 8),
(TRUE, 2025, 5, 45, 7),
(TRUE, 2025, 6, 55, 9),
(TRUE, 2025, 4, 20, 10),
(TRUE, 2025, 3, 35, 1),
(TRUE, 2025, 2, 40, 5);

-- 보호소장 회원 (shelter_head) 10개
INSERT INTO shelter_head 
(head_account, head_password, e_mail, ceo_name, ceo_name2, answer, head_phone, company_name, biz_number, cor_number, company_address, open_date, close_date, sigungu_id, questionCategory_id) 
VALUES
-- 서울: 대표자 2명
('seoulsarang', 'Shelter2025!', 'seoul@example.com', '김영희', '박철수', '치킨', '010-1111-1111', '서울사랑보호소', '111-22-33344', '990001-1234567', '서울 종로구 청운효자동 10', '2018-05-01', NULL, 1, 1),

-- 부산: 대표자 1명
('busanhappy', 'HappyBusan#1', 'busan@example.com', '박정식', NULL, '불고기', '010-2222-2222', '부산해피보호소', '222-33-44455', '880002-2345678', '부산 중구 중앙대로 50', '2019-03-15', NULL, 2, 2),

-- 대구: 대표자 2명
('daegupet', 'PetCareDg9', 'daegu@example.com', '이민호', '정하늘', '파랑', '010-3333-3333', '대구펫케어센터', '333-44-55566', '770003-3456789', '대구 중구 중앙로 20', '2020-07-01', NULL, 3, 3),

-- 인천: 대표자 1명
('incheonhope', 'HopeCenter1', 'incheon@example.com', '정미경', NULL, '민수', '010-4444-4444', '인천희망보호소', '444-55-66677', '660004-4567890', '인천 남구 주안로 100', '2017-11-11', NULL, 4, 4),

-- 광주: 대표자 2명
('gwangjupet', 'Pet&CareGJ', 'gwangju@example.com', '한경숙', '서동우', '대전', '010-5555-5555', '광주펫앤케어센터', '555-66-77788', '550005-5678901', '광주 동구 금남로 12', '2016-04-20', NULL, 5, 5),

-- 대전: 대표자 1명
('daejeonpaws', 'PawsDaejeon2', 'daejeon@example.com', '최영수', NULL, '아버지', '010-6666-6666', '대전포우즈보호소', '666-77-88899', '440006-6789012', '대전 동구 대전로 33', '2019-09-30', NULL, 6, 6),

-- 울산: 대표자 1명
('ulsangoodpet', 'GoodPetUS', 'ulsan@example.com', '이우람', NULL, '일본', '010-7777-7777', '울산굿펫센터', '777-88-99900', '330007-7890123', '울산 중구 번영로 8', '2021-02-10', NULL, 7, 7),

-- 경기: 대표자 2명
('ggshelter', 'ShelterGG8', 'gg@example.com', '박준성', '이수현', '박선생님', '010-8888-8888', '경기동물쉼터', '888-99-00011', '220008-8901234', '수원시 장안구 고색동 45', '2020-12-01', NULL, 8, 8),

-- 강원: 대표자 1명
('gwanlove', 'LoveAnimalsGW', 'gw@example.com', '송현우', NULL, '초코', '010-9999-9999', '강원러브센터', '999-00-11122', '110009-9012345', '춘천시 중앙로 77', '2018-08-15', NULL, 9, 9),

-- 충북: 대표자 2명
('chungbucare', 'CareCB5', 'cb@example.com', '류민지', '김태형', '해운대초등학교', '010-1010-1010', '충북케어보호소', '101-12-21345', '660010-0123456', '청주시 상당구 흥덕로 22', '2017-06-06', NULL, 10, 10);
 
-- 로그인 이력 (loginHistory) 10개
INSERT INTO loginHistory (ip_address, logged_at, user_id, head_id) VALUES
('203.0.113.1', '2025-09-01 08:00', 1, NULL),
('203.0.113.2', '2025-09-02 09:15', 2, NULL),
('203.0.113.3', '2025-09-03 18:45', 3, NULL),
('203.0.113.4', '2025-09-04 20:00', 4, NULL),
('203.0.113.5', '2025-09-05 07:30', 5, NULL),
('203.0.113.6', '2025-09-06 12:20', NULL, 1),
('203.0.113.7', '2025-09-07 14:55', NULL, 2),
('203.0.113.8', '2025-09-08 16:30', NULL, 3),
('203.0.113.9', '2025-09-09 11:10', 6, NULL),
('203.0.113.10','2025-09-10 19:50', NULL, 4);

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
