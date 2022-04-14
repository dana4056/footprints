package event.join.member;

import event.join.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    @DisplayName("멤버 등록 확인")
    void join(){
        //given
        Member member = new Member("tjswls8062@skuniv.ac.kr", "1234", "sunjin", "01054438062", "서울시 성북구");

        //when
        memberService.join(member);
        Member findMember = memberService.findMember("tjswls8062@skuniv.ac.kr");

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }

    
    // 에러 테스트
    @Test
    @DisplayName("전화번호 외 모든 정보 중복")     // 전화번호만 달라도 다른 객체로 생성
    void save(){
        //given
        Member member = new Member("tjswls8062@skuniv.ac.kr", "1234", "sunjin", "01054438062", "서울시 성북구");
        Member member2 = new Member("tjswls8062@skuniv.ac.kr", "1234", "sunjin", "01040569708", "서울시 성북구");

        //when
        memberService.join(member);
        memberService.join(member2);
        Member findMember = memberService.findMember_phone("01054438062");
        Member findMember2 = memberService.findMember_phone("01040569708");

        //then
        Assertions.assertThat(findMember).isNotEqualTo(findMember2);
    }

    // 에러 테스트
    @Test
    @DisplayName("모든 정보 중복")     // 모든 정보가 같을 시 동일한 객체 생성
    void save2(){
        //given
        Member member = new Member("tjswls8062@skuniv.ac.kr", "1234", "sunjin", "01054438062", "서울시 성북구");
        Member member2 = new Member("tjswls8062@skuniv.ac.kr", "1234", "sunjin", "01054438062", "서울시 성북구");

        //when
        memberService.join(member);
        memberService.join(member2);
        Member findMember = memberService.findMember_phone("01054438062");
        Member findMember2 = memberService.findMember_phone("01054438062");

        //then
        Assertions.assertThat(findMember).isEqualTo(findMember2);
    }
}