package footprints.footprints.repository.roomInfo;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.roomInfo.RoomInfo;
import footprints.footprints.domain.roomInfo.RoomInfoDTO;

import footprints.footprints.repository.member.MemberRepository;
import footprints.footprints.repository.post.PostRepository;
import footprints.footprints.service.roomInfo.RoomInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Slf4j
@Transactional
@RequiredArgsConstructor

public class RoomInfoRepositoryImpl implements RoomInfoRepository{
    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    @PersistenceContext
    private EntityManager em;

    public void save_d(String nick, Long post_id) {
        Member member = memberRepository.findByNick(nick);
        Post post = postRepository.findByPostId(post_id);

        RoomInfo roomInfo = new RoomInfo(member, post);

        em.persist(roomInfo);
    }

    public void delete_d(String nick, Long post_id) {
        Query query = em.createQuery("delete from RoomInfo r where r.member.nick = :nick and r.post.post_id = :post_id");
        query.setParameter("nick", nick).setParameter("post_id", post_id).executeUpdate();
    }

    public void delete_all(Long post_id) {
        Query query = em.createQuery("delete from RoomInfo r where r.post.post_id = :post_id");
        query.setParameter("post_id", post_id).executeUpdate();
    }
}
