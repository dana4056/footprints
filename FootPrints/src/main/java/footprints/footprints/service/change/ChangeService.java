package footprints.footprints.service.change;


import footprints.footprints.domain.member.MemberDTO;

public interface ChangeService {

    String findID(String email);
    String findPwd(String email);
    boolean changePwd(MemberDTO memberDTO);
}
