package footprints.footprints.service;

import footprints.footprints.domain.Member;

public interface MemberService {
    void join(Member member);
    boolean checkLogin(Member member);
}
