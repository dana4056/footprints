package footprints.join_example.joinservice.repository;

import footprints.join_example.joinservice.domain.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository {

    HashMap<String, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        store.put(member.getMemberId(), member); // key값을 memberId로 설정한 뒤 정보들을 store에 저장함
        return member;
    }

    @Override
    public Optional<Member> findById(String memberId) {
        return Optional.of(store.get(memberId)); // store에서 키값이 memberId인 것의 객체를 가져옴
        // Optional.of: 인자로 null값을 받지않겠다는 것임
    }

    @Override
    public Optional<Member> findByNickName(String nickName) {
        return Optional.of(store.get(nickName));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // ArrayList(크기가 가변적으로 변하는 선형리스트)로 store에 저장되어있는 값들을 불러온다.
    }

    public void clearStore() {
        store.clear();
    }
}
