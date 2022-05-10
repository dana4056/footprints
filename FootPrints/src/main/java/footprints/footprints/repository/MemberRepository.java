package footprints.footprints.repository;

import footprints.footprints.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Slf4j
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;
    public void save(Member member) {
        em.persist(member);
        log.info("======3. save {}", member.getUser_id());
    }
}
