package footprints.join_example.joinservice.service;

import footprints.join_example.joinservice.domain.Member;
import footprints.join_example.joinservice.repository.MemberRepository;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(Member member) {
        memberRepository.save(member);
    }

    public void findMember(String memberId) {
        memberRepository.findById(memberId);
    }

    public void findNickName(String nickname) {
        memberRepository.findByNickName(nickname);
    }

}
