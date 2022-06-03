package footprints.footprints.member;

import footprints.footprints.domain.member.MemberDTO;
import footprints.footprints.repository.member.MemberRepositoryImpl;
import footprints.footprints.service.signup.SignupServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;


@SpringBootTest
@Transactional
public class SignupServiceTest {


    @Autowired
    SignupServiceImpl memberService;
    @Autowired
    MemberRepositoryImpl memberRepository;


    @Test
    void save() {
        MemberDTO memberDTO = new MemberDTO("asdf", "1234", "HHJ", "010", "qwer");

        memberRepository.save(memberDTO);


    }
}
