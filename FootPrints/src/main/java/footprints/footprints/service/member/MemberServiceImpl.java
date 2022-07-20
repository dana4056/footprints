package footprints.footprints.service.member;


import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.MemberDTO;
import footprints.footprints.repository.member.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{
    private final MemberRepositoryImpl memberRepository;

    @Override
    public void join(Member member){
        memberRepository.save(member);
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

    @Transactional(readOnly = true)
    @Override
    public int loginCheck(MemberDTO memberDTO) {
        Member member = memberRepository.findByNick(memberDTO.getNick());

        if(member == null){
            return 0;  //닉네임 존재x
        }
        else{
            String real_pwd = member.getPw();

            if(memberDTO.getPw().equals(real_pwd)){
                return 1; //로그인 성금
            }
            else{
                return -1; // 비번 불일치
            }
        }
    }

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
    public void changeDBPwd(MemberDTO memberDTO) {
        String email = memberDTO.getEmail();
        log.info("email : {}", email);
        Member member = memberRepository.findByEmail(email);
        member.Update(memberDTO);
        log.info("new password : {}", member.getPassword());
        memberRepository.save(member);
    }
}
