-- 일반 유저 회원가입 
INSERT INTO `test_db`.`member` (`nick`, `area`, `email`,`pw`)
VALUES ('김선진', '서울 성북구 정릉동', 'tjswls8062@skuniv.com', 'ekdms98727^^');
INSERT INTO `test_db`.`member_roles` (`member_nick`, `roles`)
VALUES ('김선진', 'ROLE_USER');
INSERT INTO `test_db`.`member` (`nick`, `area`, `email`,`pw`)
VALUES ('정다은', '서울 구로구 개봉동', 'tjswls8062@naver.com', 'ekdms98727^^');
INSERT INTO `test_db`.`member_roles` (`member_nick`, `roles`)
VALUES ('정다은', 'ROLE_USER');

-- 관리자계정 회원가입 
INSERT INTO `test_db`.`member` (`nick`, `area`, `email`,`pw`)
VALUES ('admin', '서울 구로구 개봉동', 'dana4056@naver.com', 'ekdms98727^^');
INSERT INTO `test_db`.`member_roles` (`member_nick`, `roles`)
VALUES ('admin', 'ROLE_ADMIN');


-- 글 작성 편하게 사용자 이름쪽만 건드리면 test 가능
-- *주의: created_date는 DB에 영국시간(-9시간) 저장되어있어야함
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area` ,`lat`, `lon`)
VALUES (1, 'CHI', '2022-09-01 02:30:46', 5, 1, '중국음식 배달시간4 내용', '중국음식 배달시간4', '북악1', '2022-11-23T11:38', 0, '김선진', '서울 성북구 정릉동', 37.61507352547857,127.01321786964758 );
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area` ,`lat`, `lon`)
VALUES (2, 'KOR', '2022-09-02 02:31:46', 5, 1, '한국음식 배달시간3 내용', '한국음식 배달시간3', '북악2', '2022-11-27T11:35', 0, '김선진', '서울 성북구 정릉동', 37.61507352547857,127.01321786964758);
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area`,`lat`, `lon`)
VALUES (3, 'JAP', '2022-09-03 02:32:46', 5, 1, '일본음식 배달시간2 내용', '일본음식 배달시간2', '북악3', '2022-11-25T11:38', 0, '김선진', '서울 성북구 정릉동', 37.61507352547857,127.01321786964758);
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area`,`lat`, `lon`)
VALUES (4, 'KOR', '2022-07-27 02:33:46', 5, 1, '한국음식 배달시간1 내용', '한국음식 배달시간1', '북악4', '2022-09-23T11:35', 0, '김선진', '서울 성북구 정릉동', 37.61507352547857,127.01321786964758);
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area`,`lat`, `lon`)
VALUES (5, 'KOR', '2022-07-27 02:33:46', 5, 1, '타인이 쓴 글 내용1', '타인이 쓴 글', '북악관', '2022-11-03T11:35', 0, '정다은', '서울 성북구 정릉동', 37.61507352547857,127.01321786964758);
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area`,`lat`, `lon`)
VALUES (6, 'KOR', '2022-09-01 02:33:46', 5, 1, '타지역 한국음식 배달시간4 내용', '타지역 한국음식 배달시간4', '개봉1', '2022-11-30T11:35', 0, '정다은', '서울 구로구 개봉동', 37.49442709339608,126.85856370439684 );
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area`,`lat`, `lon`)
VALUES (7, 'CHI', '2022-09-02 02:33:46', 5, 1, '타지역 중국음식 배달시간2 내용', '타지역 중국음식 배달시간2', '개봉2', '2022-11-30T11:35', 0, '정다은', '서울 구로구 개봉동', 37.49442709339608,126.85856370439684);
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area`,`lat`, `lon`)
VALUES (8, 'JAP', '2022-09-03 02:33:46', 5, 1, '타지역 일본음식 배달시간3 내용', '타지역 일본음식 배달시간3', '개봉3', '2022-11-30T11:35', 0, '정다은', '서울 구로구 개봉동', 37.49442709339608,126.85856370439684);
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area`,`lat`, `lon`)
VALUES (9, 'KOR', '2022-09-14 18:33:46', 5, 1, '타지역 한국음식 배달시간1 내용', '타지역 한국음식 배달시간1', '개봉4', '2022-09-30T11:35', 0, '정다은', '서울 구로구 개봉동', 37.49442709339608,126.85856370439684);

INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area`,`lat`, `lon`)
VALUES (10, 'KOR', '2022-09-01 20:20:46', 5, 1, '타지역 한국음식 배달시간1 내용', '타지역 한국음식 배달시간1', '개봉4', '2022-10-15T11:35', 0, '정다은', '서울 구로구 개봉동', 37.49442709339608,126.85856370439684);
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area`,`lat`, `lon`)
VALUES (11, 'KOR', '2022-09-01 20:37:46', 5, 1, '타지역 한국음식 배달시간1 내용', '타지역 한국음식 배달시간1', '개봉4', '2022-10-15T11:35', 0, '정다은', '서울 구로구 개봉동', 37.49442709339608,126.85856370439684);


-- 공지사항 등록
INSERT INTO `test_db`.`notice` (`id`, `author`, `content`,`post_time`, `title`, `view_num`)
VALUES (1, 'admin', '공지사항1', '2022.08.13', '공지사항 내용1', 1);
INSERT INTO `test_db`.`notice` (`id`, `author`, `content`,`post_time`, `title`, `view_num`)
VALUES (2, 'admin', '공지사항2', '2022.09.14', '공지사항 내용2', 2);
INSERT INTO `test_db`.`notice` (`id`, `author`, `content`,`post_time`, `title`, `view_num`)
VALUES (3, 'admin', '공지사항3', '2022.10.15', '공지사항 내용3', 3);

