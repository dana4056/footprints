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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Slf4j
@Transactional
@RequiredArgsConstructor

public class RoomInfoRepositoryImpl {
    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    @PersistenceContext
    private EntityManager em;

    public void save_d(RoomInfoDTO roomInfoDTO) {
        Member member = memberRepository.findByNick(roomInfoDTO.getMember().getNick());
        Post post = postRepository.findByPostId(roomInfoDTO.getPost().getPost_id());

        RoomInfo roomInfo = new RoomInfo(member, post);

        log.info("RoomInfoRepositoryImpl-post: {}", roomInfo);
        em.persist(roomInfo);
    }
}
