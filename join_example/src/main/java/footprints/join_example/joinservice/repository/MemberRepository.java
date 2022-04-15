package footprints.join_example.joinservice.repository;

import footprints.join_example.joinservice.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(String memberId);
    Optional<Member> findByNickName(String nickName);
    List<Member> findAll();
}
