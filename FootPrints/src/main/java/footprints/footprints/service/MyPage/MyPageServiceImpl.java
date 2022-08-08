package footprints.footprints.service.MyPage;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.MemberDTO;
import footprints.footprints.domain.post.Post;
import footprints.footprints.repository.member.MemberRepository;
import footprints.footprints.repository.member.MemberRepositoryImpl;
import footprints.footprints.repository.myPage.MyPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
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
    public boolean changeInfo(MemberDTO memberDTO) {
        String nick = memberDTO.getNick();
        Member member = memberRepository.findByNick(nick);
        if (member == null) {
            Member byNick = memberRepository.findByNick(nick);
            byNick.InfoUpdate(memberDTO);
            memberRepository.save(byNick);
            return true;
        }
        else return false;
    }
}
