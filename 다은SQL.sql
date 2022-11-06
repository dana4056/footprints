-- 변수 생성 (날짜 : 영국시간 기준)
SET @now = DATE_ADD(NOW(), INTERVAL -9 HOUR); -- 현재
SET @after1day = @now + INTERVAL 1 DAY;		  -- 1일 후
SET @after1day = @now - INTERVAL 1 DAY;		  -- 1일 전
SET @after1hour = @now + INTERVAL 1 HOUR;	  -- 1시간 후
SET @after30min = @now + INTERVAL 30 minute;  -- 30분 후


-- 일반 유저 회원가입 
INSERT INTO `test_db`.`member` (`nick`, `area`, `email`,`pw`)
VALUES ('김**', '서울 성북구 정릉동', 'tjswls8062@skuniv.com', 'ekdms98727^^');
INSERT INTO `test_db`.`member_roles` (`member_nick`, `roles`)
VALUES ('김**', 'ROLE_USER');
INSERT INTO `test_db`.`member` (`nick`, `area`, `email`,`pw`)
VALUES ('정**', '서울 성북구 정릉동', 'dana@naver.com', 'ekdms98727^^');
INSERT INTO `test_db`.`member_roles` (`member_nick`, `roles`)
VALUES ('정**', 'ROLE_USER');
INSERT INTO `test_db`.`member` (`nick`, `area`, `email`,`pw`)
VALUES ('최**', '서울 성북구 정릉동', 'chl@naver.com', 'ekdms98727^^');
INSERT INTO `test_db`.`member_roles` (`member_nick`, `roles`)
VALUES ('최**', 'ROLE_USER');

-- 관리자계정 회원가입 
INSERT INTO `test_db`.`member` (`nick`, `area`, `email`,`pw`)
VALUES ('admin', '서울 구로구 개봉동', 'dana4056@naver.com', 'ekdms98727^^');
INSERT INTO `test_db`.`member_roles` (`member_nick`, `roles`)
VALUES ('admin', 'ROLE_ADMIN');



-- *주의: created_date는 DB에 영국시간(-9시간) 저장되어있어야함

-- [사용자 설정 지역과 포스팅 지역이 같은 경우] 
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area` ,`lat`, `lon`)
VALUES (1, 'FAS', @now, 3, 1, '맥도날드 ㅇㅇ점\n배달팁: 5000원\n최소주문금액: 13000원\n북악관 앞에서 만나요!', '타인/타지역1', '서경대 북악관 앞', @after30min+ INTERVAL 9 HOUR, 0, '정**', '서울 성북구 정릉동', 37.61587555697587,127.01178521087652 );
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area` ,`lat`, `lon`)
VALUES (2, 'CHI', @now - INTERVAL 28 minute, 2, 1, '짜장면에 탕수육 먹고싶은데, 탕수육 같이 시키실 분! 주문시 2개로 나눠서 포장해달라고 부탁드릴게요~\n음식점: OO반점\n탕수육: 13000원', '탕수육 나눠먹어요', '큰 세븐', @now + INTERVAL 9 minute + INTERVAL 9 HOUR, 0, '최**', '서울 성북구 정릉동', 37.611649765973404,127.01323708390039 );
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area` ,`lat`, `lon`)
VALUES (3, 'KOR', @now - INTERVAL 20 minute, 3, 1, '1인분 배달은 안되네요ㅜㅜ\n같이 시키실 분~', 'ㅁㅁ분식', '스콘스퀘어', @now + INTERVAL 15 minute + INTERVAL 9 HOUR, 0, '정**', '서울 성북구 정릉동', 37.614717611355935,127.01342734342833 );
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area` ,`lat`, `lon`)
VALUES (4, 'DES', @now - INTERVAL 5 minute, 5, 1, '지금 문예관에 계신 분들 중에 커피 드실 분 있나여?', '문예관에서 커피 시키실 분', '서경대 문예관', @now + INTERVAL 20 minute + INTERVAL 9 HOUR, 0, '정**', '서울 성북구 정릉동', 37.61582142125016,127.01252708676276 );
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area` ,`lat`, `lon`)
VALUES (5, 'KOR', @now - INTERVAL 40 minute, 3, 1, 'OO비빔밥\n최저 15000\n배달비 4000\n청수도서관 앞에서 나눔', '육회비빔밥', '청수도서관',  @now - INTERVAL 20 minute + INTERVAL 9 HOUR, 0, '정**', '서울 성북구 정릉동', 37.611758134997025,127.01077655924456 );

-- [사용자 설정 지역과 포스팅 지역이 다른 경우 (ex. 정릉 사람이 -> 성신게시물 올림)] 
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area` ,`lat`, `lon`)
VALUES (6, 'JAP', @now - INTERVAL 5 minute, 3, 1, '초밥 공배합시다', '초밥 공배', '성신여대 7출', @after30min+ INTERVAL 9 HOUR, 0, '정**', '서울 성북구 동선동4가', 37.592998799807035,127.01687129984442 );


-- [공지사항 등록]
INSERT INTO `test_db`.`notice` (`id`, `author`, `content`,`post_time`, `title`, `view_num`)
VALUES (1, 'admin', '발자취 서비스는 배달비가 비싸져가는 요즘, 먹고싶은 음식을 마음껏 즐길 수 있도록 도와주는 공동 배달 커뮤니티입니다.', '2022.11.03', '발자취 서비스에 오신걸 환영합니다.', 1);

