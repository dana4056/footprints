package footprints.footprints.repository;

import footprints.footprints.domain.Member;
import footprints.footprints.domain.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Arrays;
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
    public int canLogin(MemberDTO memberDTO){

        TypedQuery<Member> memberTypedQuery = em.createQuery("select m from Member m where m.nick = :nick", Member.class)
                .setParameter("nick", memberDTO.getNick());
        List<Member> resultList = memberTypedQuery.getResultList();
        //Member my_member = em.find(Member.class, memberDTO.getEmail());

        if(resultList.size() == 0){
            log.info("존재하지 않는 닉네임");
            return 0; //안돼
        }
        else{
            String real_pwd = resultList.get(0).getPw();
            if(memberDTO.getPw().equals(real_pwd)){
                return 1; //로그인 됨
            }
            else{
                log.info("비밀번호가 일치하지 않음");
                return -1; //안돼
            }
        }
    }

    @Override
    public boolean existsByEmail(String email) {

        TypedQuery<Member> memberTypedQuery = em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email);
        List<Member> resultList = memberTypedQuery.getResultList();

        if (resultList.size() == 0) return true;
        else return false;
    }

    @Override
    public boolean existsByNick(String nick) {

        TypedQuery<Member> memberTypedQuery = em.createQuery("select m from Member m where m.nick = :nick", Member.class)
                .setParameter("nick", nick);
        List<Member> resultList = memberTypedQuery.getResultList();

        if (resultList.size() == 0) return true;
        else return false;
    }
}
