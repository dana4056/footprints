package footprints.footprints.repository;

import footprints.footprints.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository

public class MemberRepository {

    @PersistenceContext

    private EntityManager em;
    public void save(Member member) {
        em.persist(member);
    }
}
