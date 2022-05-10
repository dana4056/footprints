package footprints.footprints.service;

import footprints.footprints.domain.Member;
import footprints.footprints.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    public void join(Member member){
        log.info("======2. save {}", member.getUser_id());
        memberRepository.save(member);
    }
}
