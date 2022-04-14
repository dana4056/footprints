package event.join.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {

    private static Map<String, Member> store = new HashMap<>();
    private static Map<String, Member> store_ex = new HashMap<>();    //에러 체크 위한 코드

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);      // key값으로 memberId, value는 member 객체
        store_ex.put(member.getPhone(), member);
    }

    @Override
    public Member findbyId(String memberId) { 
        return store.get(memberId);             // key값이 memberId인 value 반환
    }

    @Override
    public Member findbyPhone(String memberPhone) {
        return store_ex.get(memberPhone);
    }


}
