package footprints.footprints.repository;

import footprints.footprints.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Slf4j
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public boolean canLogin(Member member){
        Member my_member = em.find(Member.class, member.getEmail());
        String real_pwd = my_member.getPw();
        if(member.getPw() == real_pwd){
            return true;
        }
        else{
            return false;
        }
    }
}
