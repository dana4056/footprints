package footprints.footprints.repository.member;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.MemberDTO;

public interface MemberRepository {

    void save(MemberDTO memberDTO);

    void save1(Member member);
    Member findByEmail(String email);
    Member findByNick(String nick);

    void delete(MemberDTO memberDTO);

    boolean changeDBPwd(MemberDTO memberDTO);

}
