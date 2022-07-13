package footprints.footprints.service.change;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.MemberDTO;
import footprints.footprints.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ChangeServiceImpl implements ChangeService{

    private final MemberRepository memberRepository;


    @Override
    public String findID(String email) {
        Member member = memberRepository.findByEmail(email);
        if(member == null){
            return null;
        }
        else{
            return member.getNick();
        }
    }

    @Override
    public String findPwd(String email) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            return null;
        }
        else{
            return member.getEmail();
        }
    }

    @Override
    public boolean changePwd(MemberDTO memberDTO) {
        boolean newPwdChange = memberRepository.changeDBPwd(memberDTO);
        if (newPwdChange) {
            return true;   // 비밀번호 변경 성공
        }
        else{
            return false;   // 실패
        }
    }
}
