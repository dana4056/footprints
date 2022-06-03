package footprints.footprints.repository.member;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

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
        Member member = new Member(memberDTO.getNick(),
                                   memberDTO.getEmail(),
                                   memberDTO.getPw(),
                                   memberDTO.getArea());
        em.persist(member);
    }

    @Override
    public Member findByNick(String nick) {

        TypedQuery<Member> memberTypedQuery = em.createQuery("select m from Member m where m.nick = :nick", Member.class)
                .setParameter("nick", nick);
        List<Member> resultList = memberTypedQuery.getResultList();

        if (resultList.size() == 0) return null;
        else return resultList.get(0);
    }

    @Override
    public Member findByEmail(String email) {

        TypedQuery<Member> memberTypedQuery = em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email);
        List<Member> resultList = memberTypedQuery.getResultList();

        if (resultList.size() == 0) return null;
        else return resultList.get(0);
    }
}
