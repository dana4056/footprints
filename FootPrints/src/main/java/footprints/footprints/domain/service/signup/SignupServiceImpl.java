package footprints.footprints.domain.service.signup;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.MemberDTO;
import footprints.footprints.domain.repository.member.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SignupServiceImpl implements SignupService {

    @Autowired
    private final MemberRepositoryImpl memberRepository;

    @Override
    public void join(MemberDTO memberDTO){
        memberRepository.save(memberDTO);
    }


    @Override
    public boolean emailOverlapCheck(String email) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            return true; // 아이디 생성 가능
        }
        else{
            return false; // 아이디 생성 불가
        }
    }


    @Override
    public boolean nickOverlapCheck(String nick) {
        Member member = memberRepository.findByNick(nick);
        if (member == null) {
            return true;  // 아이디 생성 가능
        }
        else return false;
    }
}
