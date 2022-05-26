package footprints.footprints.member;

import footprints.footprints.domain.Member;
import footprints.footprints.domain.MemberDTO;
import footprints.footprints.repository.MemberRepositoryImpl;
import footprints.footprints.service.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;


@SpringBootTest
@Transactional
@Rollback(false)
public class MemberServiceTest {


    @Autowired
    MemberServiceImpl memberService;
    @Autowired
    MemberRepositoryImpl memberRepository;


    @Test
    void save() {
        MemberDTO memberDTO = new MemberDTO("asdf", "1234", "HHJ", "010", "qwer");

        memberRepository.save(memberDTO);


    }
}
