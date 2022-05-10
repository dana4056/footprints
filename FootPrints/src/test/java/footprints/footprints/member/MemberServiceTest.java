package footprints.footprints.member;

import footprints.footprints.domain.Member;
import footprints.footprints.repository.MemberRepository;
import footprints.footprints.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@SpringBootTest
@Transactional
public class MemberServiceTest {


    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    void save() {
        Member member = new Member("asdf", "1234", "HHJ", "010", "qwer");

 //           memberService.join(member);
          memberRepository.save(member);


    }
}
