package footprints.footprints.service.change;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.MemberChangeDTO;
import footprints.footprints.repository.member.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ChangeServiceImpl implements ChangeService{

    private final MemberRepositoryImpl memberRepository;


    @Override
    public String findID(String email) {
        Member member = memberRepository.findByEmail(email);
        return member.getEmail();
    }

    @Override
    public String findPwd(String email) {
        Member member = memberRepository.findByEmail(email);
        return member.getPw();
    }

    @Override
    public boolean changePwd(MemberChangeDTO memberChangeDTO) {
        boolean newPwdChange = memberRepository.changeDBPwd(memberChangeDTO);
        if (newPwdChange) {
            return true;   // 비밀번호 변경 성공
        }
        else{
            return false;   // 실패
        }
    }
}
