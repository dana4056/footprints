package footprints.footprints.repository;

import footprints.footprints.domain.Member;

public interface MemberRepository {

    void save(Member member);

    boolean canLogin(Member member);
}
