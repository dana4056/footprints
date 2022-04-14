package event.join.member;

public interface MemberService {

    void join(Member member);

    Member findMember(String memberId);

    Member findMember_phone(String memberPhone);
}
