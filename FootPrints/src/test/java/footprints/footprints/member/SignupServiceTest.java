package footprints.footprints.member;

import footprints.footprints.domain.member.MemberDTO;
import footprints.footprints.repository.member.MemberRepositoryImpl;
import footprints.footprints.service.signup.SignupServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;


@SpringBootTest
@Transactional
@Rollback(false)
public class SignupServiceTest {

    @Autowired
    SignupServiceImpl memberService;
    @Autowired
    MemberRepositoryImpl memberRepository;

    @Test
    void save() {
        MemberDTO memberDTO = new MemberDTO("Hyukjin", "gurwlszx@naver.com", "1234", "성북구 정릉동");

        memberRepository.save(memberDTO);


    }
}
