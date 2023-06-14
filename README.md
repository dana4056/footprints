발자취
⭐개요
발자취는 공동 배달 주문을 위한 실시간 채팅 기반 커뮤니티 웹 서비스입니다.

📍 개발기간 : 2022.06 ~ 2022.11 (6개월)

📍 개발인원 : 5명 (팀 프로젝트)

📍 시연영상 : https://youtu.be/pmrt-P2ep-I

📍 API 및 라우팅 URL 명세 : https://docs.google.com/spreadsheets/d/1Dnjs-u6hf7sGdML0f_NopK-N3zzGGXAfZ_K_bxC4Rnc/edit?usp=sharing


⭐개발환경
종류	환경
언어	Java(JDK11), HTML/CSS, Javascript
프레임워크	Spring Boot, Spring Security, Vue.js
서버	Apache Tomcat 9.0, Amazon EC2
DB	MySQL 8.0, JPA, Amazon RDS
IDE 및 Tool	IntelliJ IDEA, Visual Studio Code, MySQL Workbench
API 및 라이브러리	Restful API, Kakao map API
⭐역할분배
담당	내용
정유석
(백엔드)	게시글 목록 조회 및 업데이트, 게시글 인원 업데이트 기능
DB 설계 및 구축
DTO 설계 및 수정
마이페이지 기능, 채팅방 생성 및 업데이트 기능
홍혁진
(백엔드)	게시글 조회수 올리기 기능
게시글 등록
Socket을 활용한 채팅 기능
DB 설계 및 구축
이지원
(프론트엔드)	화면 디자인 및 퍼블리싱(채팅 , 게시글 작성, 마이페이지)
Socket을 활용한 채팅 기능
이벤트 처리
카카오 맵 API를 활용한 지도, 주소데이터 처리
Vuex를 통한 상태 관리
Vue.js 기반의 컴포넌트 구성, Axois를 이용한 비동기 통신
김선진
(백엔드 및 프론트엔드)	AWS의 EC2와 RDS를 이용한 서비스 배포
게시물 정렬
닉네임, 아이디 중복 확인 기능
아이디, 비밀번호 찾기 기능
Socket을 활용한 채팅 기능
프로그램 오류 검증(QA)
프로그램 효율성 개선(QA)
Axois를 이용한 비동기 통신
Vuex를 통한 상태 관리
정다은
(백엔드 및 프론트엔드)	AWS의 EC2와 RDS를 이용한 서비스 배포
회원가입, 로그인 기능
JWT와 Spring Security를 활용한 사용자 권한 관리 및 인증
SPA로 인한 새로고침 오류 개선
화면 디자인 및 퍼블리싱
(채팅 , 게시글 작성, 마이페이지 제외 모든 페이지)
카카오 맵 API를 활용한 지도, 주소데이터 처리
Vuex를 통한 상태 관리
Vue.js 기반의 컴포넌트 구성, Axois를 이용한 비동기 통신
⭐ERD
image


주요 상세 페이지
🖥 배달 주문 같이 하기 - 리스트뷰
image

🖥 배달 주문 같이 하기 - 글 작성
함께 음식을 주문할 사용자를 찾기 위한 글을 작성합니다.

image

🖥 배달 주문 같이 하기 - 상세 페이지 & 수정 페이지
image

🖥 채팅방
다른 사용자들과 실시간으로 소통할 수 있습니다.

image
