package footprints.footprints.repository;

import footprints.footprints.domain.Member;
import footprints.footprints.domain.MemberDTO;

public interface MemberRepository {

    void save(MemberDTO memberDTO);

    int canLogin(MemberDTO memberDTO);

    boolean existsByEmail(String email);

    boolean existsByNick(String nick);
}
