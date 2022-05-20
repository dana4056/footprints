package footprints.delivery.member;

import footprints.delivery.domain.Member;
import footprints.delivery.repository.MemberRepository;
import footprints.delivery.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@SpringBootTest
@Transactional
@Rollback(false)
public class MemberServiceTest {


    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    void save() {
        Member member3 = new Member("Hyuk", "1234", "HHJ", "9052", "은평구");

        memberService.join(member3);

    }
}
