package footprints.footprints.service.MyPage;

import footprints.footprints.domain.member.MemberDTO;
import footprints.footprints.domain.post.Post;

import java.util.List;

public interface MyPageService {

    List<Post> getMyPost(String nick);

    List<Post> getAttendPost(String nick);

    boolean changeInfo(MemberDTO memberDTO);
}
