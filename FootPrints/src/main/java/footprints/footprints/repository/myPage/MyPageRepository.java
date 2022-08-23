package footprints.footprints.repository.myPage;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.post.Post;

import java.util.List;

public interface MyPageRepository {

    List<Post> getMyPost(String nick);

    List<Post> getAttendPost(String nick);

    void changeInfo(Member member);
}
