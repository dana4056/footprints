package footprints.footprints.service.member;

import footprints.footprints.domain.member.DTO.ReqChangePwDTO;
import footprints.footprints.domain.member.DTO.ReqLoginMemberDTO;
import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.DTO.MemberDTO;

public interface MemberService {

    void join(MemberDTO memberDTO);
    boolean emailOverlapCheck(String email);
    boolean nickOverlapCheck(String nick);
    int loginCheck(ReqLoginMemberDTO loginMemberDTO);
    String findArea(String nick);
    String findID(String email);
    String findPwd(String email);
    void changeDBPwd(ReqChangePwDTO changePwDTO);
    void like_postUpdate(String nick, Long post_id);
}
