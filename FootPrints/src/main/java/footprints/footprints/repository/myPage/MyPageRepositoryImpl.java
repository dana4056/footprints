package footprints.footprints.repository.myPage;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.post.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Slf4j
public class MyPageRepositoryImpl implements MyPageRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Post> getMyPost(String nick) {
        TypedQuery<Post> postList = em.createQuery("select p from Post p where p.member.nick = :nick",
                Post.class).setParameter("nick", nick);

        List<Post> resultList = postList.getResultList();

        return resultList;
    }

    @Override
    public List<Post> getAttendPost(String nick) {
        TypedQuery<Post> postList = em.createQuery("select r.post from RoomInfo r where r.member.nick = :nick",
                Post.class).setParameter("nick", nick);

        List<Post> resultList = postList.getResultList();

//        log.info("-------------getAttendPost-------- {}", resultList);
//
//
//        TypedQuery<RoomInfo> postList2 = em.createQuery("select r from RoomInfo r where r.member.nick = :nick",
//                RoomInfo.class).setParameter("nick", nick);
//
//        List<RoomInfo> resultList2 = postList2.getResultList();
//
//        log.info("-------------getAttendPost2-------- {}", resultList2);
        return resultList;
    }

    @Override
    public void changeInfo(Member member) {
        em.merge(member);
    }

    @Override
    public List<Post> getLikePost(String nick) {
        TypedQuery<Post> result = em.createQuery("select m.like_posts from Member m where m.nick = :nick",
                Post.class).setParameter("nick", nick);

        List<Post> resultList = result.getResultList();
        return resultList;
    }


}
