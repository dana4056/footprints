package footprints.footprints.repository.member;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.DTO.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Slf4j
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(MemberDTO memberDTO) {
//        Member member = new Member(memberDTO.getNick(), memberDTO.getEmail(), memberDTO.getPw(),memberDTO.getArea());
        Member member = memberDTO.toEntity();
//        member.setRoles();
        if(member.getNick() == null){
            log.info("새로운 member 저장");
            em.persist(member);
        }
        else{
            log.info("기존의 member 수정");
            em.merge(member);
        }
        log.info("--------saveSuccess-----------");
    }


    @Override
    public Member findByNick(String nick) {
        return em.find(Member.class, nick);
    }

    @Override
    public Member findByEmail(String email) {

        TypedQuery<Member> memberTypedQuery = em.createQuery(
                "select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email);
        List<Member> resultList = memberTypedQuery.getResultList();

        if (resultList.size() == 0) return null;
        else return resultList.get(0);
    }

    @Override
    public void delete(MemberDTO memberDTO){
//        Member member = memberDTO.toEntity();
        em.remove(findByEmail(memberDTO.getEmail()));
        log.info("--------deleteSuccess-----------");
    }

    @Override
    public void update_likes(String nick, Long post_id) {
        Member member = findByNick(nick);
        List<Long> like_posts = member.getLike_posts();
        like_posts.add(post_id);
    }
}
