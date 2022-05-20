package footprints.footprints.service;

import footprints.footprints.domain.Member;
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
    public void join(Member member){
        memberRepository.save(member);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkLogin(Member member) {
        boolean canLogin = memberRepository.canLogin(member);

        if(!canLogin){
            return false;
//            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        else{
            return true;
        }
    }
}
