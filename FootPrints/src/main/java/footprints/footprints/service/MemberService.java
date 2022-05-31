package footprints.footprints.service;

import footprints.footprints.domain.Member;
import footprints.footprints.domain.MemberDTO;

public interface MemberService {
    void join(MemberDTO memberDTO);
    int loginCheck(MemberDTO memberDTO);
    boolean emailOverlapCheck(String email);
    boolean nickOverlapCheck(String nick);
}
