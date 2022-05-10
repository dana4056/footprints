mysql 설치

root 스키마 생성

프로젝트 내 

application.yaml 파일의 datasource 내의 password -> 본인이 설정한 비밀번호로 변경

member.java 파일의 @Column(name = "member_id" 삭제

test 파일의 save 문장 주석처리, join 주석 해제



FootprintsApplication 실행(중단X) -> test 실행

-> 테이블 잘 생성되고 멤버 삽입 되는지 확인
