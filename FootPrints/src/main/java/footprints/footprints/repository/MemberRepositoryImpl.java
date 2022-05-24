package footprints.footprints.repository;

import footprints.footprints.domain.Member;
import footprints.footprints.domain.MemberDTO;
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
        Member member = new Member(memberDTO.getEmail(),
                                   memberDTO.getPw(),
                                   memberDTO.getNick(),
                                   memberDTO.getPhone(),
                                   memberDTO.getArea());
        em.persist(member);
    }

    @Override
    public boolean canLogin(MemberDTO memberDTO){
        Member my_member = em.find(Member.class, memberDTO.getEmail());

        if(my_member == null){
            return false; //안돼
        }
        else{
            String real_pwd = my_member.getPw();
            if(memberDTO.getPw() == real_pwd){
                return true; //로그인 됨
            }
            else{
                return false; //안돼
            }
        }
    }

    @Override
    public boolean existsByEmail(MemberDTO memberDTO) {
        String email = memberDTO.getEmail();

        TypedQuery<Member> memberTypedQuery = em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email);
        List<Member> resultList = memberTypedQuery.getResultList();

        if (resultList.size() == 0) return true;
        else return false;
    }

    @Override
    public boolean existsByNick(MemberDTO memberDTO) {
        String nick = memberDTO.getNick();

        TypedQuery<Member> memberTypedQuery = em.createQuery("select m from Member m where m.nick = :nick", Member.class)
                .setParameter("nick", nick);
        List<Member> resultList = memberTypedQuery.getResultList();

        if (resultList.size() == 0) return true;
        else return false;
    }
}
