package footprints.footprints.domain.service.signup;

import footprints.footprints.domain.member.MemberDTO;

public interface SignupService {
    void join(MemberDTO memberDTO);
    boolean emailOverlapCheck(String email);
    boolean nickOverlapCheck(String nick);
}
