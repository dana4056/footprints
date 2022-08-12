package footprints.footprints.service.member;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.MemberDTO;

public interface MemberService {

    void join(Member member);
    boolean emailOverlapCheck(String email);
    boolean nickOverlapCheck(String nick);
    int loginCheck(MemberDTO memberDTO);
    String findArea(String nick);
    String findID(String email);
    String findPwd(String email);
    void changeDBPwd(MemberDTO memberDTO);

}
