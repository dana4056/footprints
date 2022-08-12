package footprints.footprints.service.MyPage;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.MemberDTO;
import footprints.footprints.domain.post.Post;
import footprints.footprints.repository.member.MemberRepository;
import footprints.footprints.repository.member.MemberRepositoryImpl;
import footprints.footprints.repository.myPage.MyPageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
        log.info("-------------getAttendPost-------- {}", attendPost);
        return attendPost;
    }

    @Override
    public boolean changeInfo(MemberDTO memberDTO) {
        log.info("-----------changeInfo0------{}", memberDTO);
        String nick = memberDTO.getNick();
        log.info("-----------changeInfo1------{}", nick);
        Member member = memberRepository.findByNick(nick);
<<<<<<< HEAD
        log.info("-----------changeInfo2------{}", member);
        if (member == null) {
            Member byNick = memberRepository.findByNick("춘식");
            log.info("-----------changeInfo3------{}", byNick);
            byNick.InfoUpdate(memberDTO);
            log.info("-----------changeInfo4------{}", byNick);
=======
        if (member != null) {
            Member byNick = memberRepository.findByNick(nick);
            //byNick.InfoUpdate(memberDTO);
>>>>>>> 2c4388a1aecfbdfa45304960895f89deac16190e
            memberRepository.save(byNick);
            log.info("-----------changeInfo5------");
            return true;
        }
        else return false;
    }
}
