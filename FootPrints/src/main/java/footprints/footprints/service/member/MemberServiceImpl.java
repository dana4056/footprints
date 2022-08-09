package footprints.footprints.service.member;


import footprints.footprints.domain.member.DTO.ReqChangePwDTO;
import footprints.footprints.domain.member.DTO.ReqLoginMemberDTO;
import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.DTO.MemberDTO;
import footprints.footprints.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

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

    @Transactional(readOnly = true)
    @Override
    public int loginCheck(ReqLoginMemberDTO loginMemberDTO) {
        Member member = memberRepository.findByNick(loginMemberDTO.getNick());

        if(member == null){
            return 0;  //닉네임 존재x
        }
        else{
            String real_pwd = member.getPw();

            if(loginMemberDTO.getPw().equals(real_pwd)){
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
    public void changeDBPwd(ReqChangePwDTO changePwDTO) {
        String email = changePwDTO.getEmail();
        Member member = memberRepository.findByEmail(email);
        log.info("before password : {}", member.getPassword());
        log.info("new password : {}", changePwDTO.getPw());
        MemberDTO changePwMemberDTO = new MemberDTO(member.getNick(), email, changePwDTO.getPw(), member.getArea());
        memberRepository.save(changePwMemberDTO);
    }
}
