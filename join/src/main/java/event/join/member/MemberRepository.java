package event.join.member;

public interface MemberRepository {

    void save(Member member);

    Member findbyId(String memberId);

    Member findbyPhone(String memberPhone);
}
