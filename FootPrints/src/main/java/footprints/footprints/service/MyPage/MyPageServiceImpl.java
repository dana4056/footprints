package footprints.footprints.service.MyPage;

import footprints.footprints.domain.member.DTO.MemberDTO;
import footprints.footprints.domain.member.Member;

import footprints.footprints.domain.post.Post;
import footprints.footprints.repository.member.MemberRepository;
import footprints.footprints.repository.myPage.MyPageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    private final MyPageRepository myPageRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<Post> getMyPost(String nick) {
        List<Post> myPost = myPageRepository.getMyPost(nick);
        return myPost;
    }

    @Override
    public List<Post> getAttendPost(String nick) {
        List<Post> attendPost = myPageRepository.getAttendPost(nick);
        return attendPost;
    }

    @Override
    public void changeInfo(MemberDTO memberDTO) {
        String nick = memberDTO.getNick();
        Member member = memberRepository.findByNick(nick);
        member.InfoUpdate(memberDTO);
        myPageRepository.changeInfo(member);
    }

//    @Override
//    public void changeDBPwd(MemberDTO memberDTO) {
//        String email = memberDTO.getEmail();
//        Member member = memberRepository.findByEmail(email);
//        log.info("before password : {}", member.getPassword());
//        member.Update(memberDTO);
//        log.info("new password : {}", member.getPassword());
//        memberRepository.save(member);
//    }
}
