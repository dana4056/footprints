package footprints.footprints.service;

import footprints.footprints.domain.Member;
import footprints.footprints.domain.MemberDTO;
import footprints.footprints.repository.MemberRepository;
import footprints.footprints.repository.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Autowired
    private final MemberRepositoryImpl memberRepository;

    @Override
    public void join(MemberDTO memberDTO){
        memberRepository.save(memberDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean loginCheck(MemberDTO memberDTO) {
        boolean canLogin = memberRepository.canLogin(memberDTO);

        if(canLogin){
            return true; // 로그인 성공
        }
        else{
            return false;
            //            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }

    @Override
    public boolean emailOverlapCheck(String email) {
        boolean emailDuplicate = memberRepository.existsByEmail(email);
        if (emailDuplicate)
            return true; // 아이디 생성 가능
        else
            return false; // 아이디 생성 불가
    }


    @Override
    public boolean nickOverlapCheck(String nick) {
        boolean emailDuplicate = memberRepository.existsByNick(nick);
        if (emailDuplicate) {
            return true;  // 아이디 생성 가능
        }
        else return false;  //throw new IllegalStateException("이미 존재하는 닉네임입니다.");
    }
}
