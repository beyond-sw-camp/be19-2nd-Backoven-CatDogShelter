
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
    activation_date VARCHAR(20) NOT NULL,    -- 정지 해제일
    rating_id INT NOT NULL,                  -- 회원등급 FK
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
    logged_at VARCHAR(20) NOT NULL,
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
    CONSTRAINT fk_apl_head FOREIGN KEY (head_id) REFERENCES shelter_head(head_id)
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
