package footprints.footprints.repository;

import footprints.footprints.domain.Member;
import footprints.footprints.domain.MemberDTO;
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
    public void save(MemberDTO memberDTO) {
        em.persist(memberDTO);
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
        Member member = em.find(Member.class, email);

        if (member == null) return true; //아이디 생성 가능
        else return false; // 아이디 생성 불가
    }

    @Override
    public boolean existsByNick(MemberDTO memberDTO) {
        String nick = memberDTO.getNick();
        Member member = em.find(Member.class, nick);

        if (member == null) return true;
        else return false;
    }
}
