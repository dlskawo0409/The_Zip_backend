create schema  IF NOT EXISTS ssafyhome;
use ssafyhome;


CREATE TABLE IF NOT EXISTS  member (
    member_id   BIGINT NOT NULL AUTO_INCREMENT,
    email       VARCHAR(50),
    password    VARCHAR(60),
    nickname    VARCHAR(20),
    gender      VARCHAR(10),
    isBlocked   varchar(3) DEFAULT "F",
    role        VARCHAR(10),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at	DATETIME,
    college_id bigint,
    foreign key (college_id) references college(college_id)  on delete cascade,
    PRIMARY KEY (member_id)
);

CREATE TABLE IF NOT EXISTS image (
    image_id    BIGINT NOT NULL AUTO_INCREMENT,
    image_url   VARCHAR(255),
    image_type	VARCHAR(10),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    reference_id BIGINT,
    PRIMARY KEY (image_id)
);

CREATE TABLE IF NOT EXISTS college (
    college_id INT AUTO_INCREMENT PRIMARY KEY,
    college_name VARCHAR(255),
    college_english_name VARCHAR(255),
    branch_type VARCHAR(255),
    region_name VARCHAR(255),
    road_address VARCHAR(255),
    homepage VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS charter
(	charter_id	bigint NOT NULL AUTO_INCREMENT,
	pre_code VARCHAR(5) NOT NULL,
    post_code VARCHAR(5) NOT NULL,
    charter_kind	varchar(5) not null,
    floor			varchar(3) not null,
    deal_year		int,
    deal_month		int,
    deal_day		int,
    deposit			int,
    rent			int,
    name			varchar(50),
	size float DEFAULT NULL,
    construction_year int,
     building_use	varchar(10),
     member_id bigint,
     charter_dong	varchar(20),
     charter_gu		varchar(20),
     bonbun			int,
     bubun			int,
PRIMARY KEY (charter_id));


CREATE TABLE IF NOT EXISTS dormitory
(	dormitory_id	bigint NOT NULL AUTO_INCREMENT,
	name			varchar(20),
    room_number		varchar(15),
    room_count		bigint,
    capacity		bigint,
    deposit			bigint,
    rent			bigint,
    yearly_rent		bigint,
    maintenance		bigint,
    dormitory_kind	varchar(10),
    college_id		bigint,
    sum				bigint(10),
PRIMARY KEY (dormitory_id));

CREATE TABLE IF NOT EXISTS amenity
(	amenity_id	bigint NOT NULL AUTO_INCREMENT,
	dormitory_id BIGINT,
	name		varchar(15),
    foreign key (dormitory_id) references dormitory(dormitory_id) on delete cascade,
PRIMARY KEY (amenity_id));


CREATE TABLE IF NOT EXISTS refresh (
    refresh_id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    refresh VARCHAR(512) NOT NULL,  -- 토큰을 위한 충분한 길이 설정
    expiration varchar(255),
    PRIMARY KEY (refresh_id)
);

CREATE TABLE IF NOT EXISTS board (
    board_id INT AUTO_INCREMENT PRIMARY KEY, -- 게시판 ID (기본 키)
    title VARCHAR(255) NOT NULL,             -- 제목
    content TEXT NOT NULL,                   -- 내용
    author VARCHAR(100),            -- 작성자
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at	DATETIME,
    views INT DEFAULT 0
);

CREATE TABLE IF NOT exists comment (
    comment_id INT AUTO_INCREMENT PRIMARY KEY, -- 댓글 ID (기본 키)
    board_id INT NOT NULL,                      -- 게시판 ID (외래 키)
    member_id bigint not null,  
    content TEXT NOT NULL,                      -- 댓글 내용
    author VARCHAR(100) NOT NULL,               -- 작성자
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성 시간 
    ideleted_at	DATETIME,          -- 삭제 여부
    FOREIGN KEY (board_id) REFERENCES board(board_id) ON DELETE CASCADE -- 게시글 삭제 시 댓글도 삭제
);

CREATE table IF NOT EXISTS interest_house (
	interest_house_id	BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id			bigint,
    apt_seq				varchar(20),
    FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE
);

CREATE table IF NOT EXISTS interest_charter (
	interest_charter_id	BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id			bigint,
    charter_id			bigint,
    FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE,
	FOREIGN KEY (charter_id) REFERENCES charter(charter_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS interest_area(
	interest_area_id	BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id		bigint,
    dong_code 		varchar(10) unique,
    name			varchar(25),
    FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE, 
    FOREIGN key (dong_code) REFERENCES dongcodes(dong_code) ON DELETE CASCADE
);


ALTER TABLE houseinfos ADD FULLTEXT INDEX apt_nm_fulltext_idx (apt_nm) WITH PARSER ngram;
ALTER TABLE housedeals ADD FULLTEXT INDEX apt_seq_fulltext_idx (apt_seq) WITH PARSER ngram;
ALTER TABLE charter ADD FULLTEXT INDEX name_fulltext_idx (name) WITH PARSER ngram;
CREATE INDEX idx_deal_date ON housedeals (deal_year, deal_month, deal_day);
ALTER TABLE interest_house MODIFY apt_seq VARCHAR(20) COLLATE utf8mb4_0900_ai_ci;

ALTER TABLE houseinfos
ADD floor VARCHAR(3),
ADD exclu_use_ar DECIMAL(7,2),
ADD deal_amount VARCHAR(10);

UPDATE houseinfos hi
JOIN (
    SELECT hd.*
    FROM housedeals hd
    INNER JOIN (
        SELECT apt_seq, MAX(STR_TO_DATE(CONCAT(deal_year, '-', deal_month, '-', deal_day), '%Y-%c-%e')) AS max_date
        FROM housedeals
        GROUP BY apt_seq
    ) latest_deals ON hd.apt_seq = latest_deals.apt_seq
    AND STR_TO_DATE(CONCAT(hd.deal_year, '-', hd.deal_month, '-', hd.deal_day), '%Y-%c-%e') = latest_deals.max_date
) latest_hd ON hi.apt_seq = latest_hd.apt_seq
SET
    hi.floor = latest_hd.floor,
    hi.exclu_use_ar = latest_hd.exclu_use_ar,
    hi.deal_amount = latest_hd.deal_amount;




commit;


