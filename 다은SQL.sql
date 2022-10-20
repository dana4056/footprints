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
VALUES (1, 'CHI', '2022-10-15 02:30:46', 5, 1, '내용', '타인/타지역1', '서경대 북악관', '2022-11-23T11:38', 0, '김선진', '서울 성북구 정릉동', 37.616,127.0119 );
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area` ,`lat`, `lon`)
VALUES (2, 'KOR', '2022-10-15 02:31:46', 5, 1, '내용', '타인/타지역2', '서경대 교문', '2022-10-17T11:35', 0, '김선진', '서울 성북구 정릉동', 37.6136,127.0127);
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area`,`lat`, `lon`)
VALUES (3, 'JAP', '2022-10-15 02:32:46', 5, 1, '내용', '타인/타지역3', '서경대 앞 큰 세븐', '2022-11-25T11:38', 0, '김선진', '서울 성북구 정릉동', 37.6117,127.0131);
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area`,`lat`, `lon`)
VALUES (4, 'KOR', '2022-10-15 02:33:46', 5, 1, '내용', '타인/타지역4', '서경대 스콘스퀘어', '2022-09-23T11:35', 0, '김선진', '서울 성북구 정릉동', 37.6149,127.0134);

INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area`,`lat`, `lon`)
VALUES (5, 'KOR', '2022-10-15 02:33:46', 5, 1, '내용', '본인/본인지역1', '개봉역 1번출구', '2022-11-03T11:35', 0, '정다은', '서울 성북구 정릉동', 37.4944,126.8587);
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area`,`lat`, `lon`)
VALUES (6, 'KOR', '2022-10-15 02:33:46', 5, 1, '내용', '본인/본인지역2', '개웅중 교문 앞', '2022-11-30T11:35', 0, '정다은', '서울 구로구 개봉동', 37.4873,126.8515);
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area`,`lat`, `lon`)
VALUES (7, 'CHI', '2022-10-15 02:33:46', 5, 1, '내용', '본인/본인지역3', '대원주유소 앞', '2022-10-17T11:35', 0, '정다은', '서울 구로구 개봉동', 37.4863,126.8563);
INSERT INTO `test_db`.`post` (`post_id`, `category`, `created_date`,`max_person_num`, `participant_num`, `post_content`, `post_name`, `take_loc`, `valid_time`, `view_num`, `member_nick`, `post_area`,`lat`, `lon`)
VALUES (8, 'JAP', '2022-10-15 02:33:46', 5, 1, '내용', '본인/본인지역4', '이디아 개봉중앙점', '2022-10-17T11:35', 0, '정다은', '서울 구로구 개봉동', 37.4864,126.8549);


-- 공지사항 등록
INSERT INTO `test_db`.`notice` (`id`, `author`, `content`,`post_time`, `title`, `view_num`)
VALUES (1, 'admin', '공지사항1', '2022.08.13', '공지사항 내용1', 1);
INSERT INTO `test_db`.`notice` (`id`, `author`, `content`,`post_time`, `title`, `view_num`)
VALUES (2, 'admin', '공지사항2', '2022.09.14', '공지사항 내용2', 2);
INSERT INTO `test_db`.`notice` (`id`, `author`, `content`,`post_time`, `title`, `view_num`)
VALUES (3, 'admin', '공지사항3', '2022.10.15', '공지사항 내용3', 3);

