package footprints.footprints.repository.roomInfo;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.roomInfo.RoomInfo;
import footprints.footprints.domain.roomInfo.RoomInfoDTO;
import footprints.footprints.repository.member.MemberRepository;
import footprints.footprints.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Repository
@Slf4j
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

        postRepository.plus_participant(post_id);

        em.persist(roomInfo);
    }

    public void delete_d(String nick, Long post_id) {
        Member member = memberRepository.findByNick(nick);
        Post post = postRepository.findByPostId(post_id);
        RoomInfo roomInfo = new RoomInfo(member, post);

        postRepository.minus_participant(post_id);

        em.remove(roomInfo);
    }

    public void delete_all(Long post_id) {
        Query query = em.createQuery("delete from RoomInfo r where r.post.post_id = :post_id");
        query.setParameter("post_id", post_id).executeUpdate();
    }
}
