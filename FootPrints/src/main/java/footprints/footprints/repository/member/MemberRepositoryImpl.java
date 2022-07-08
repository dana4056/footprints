package footprints.footprints.repository.member;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.MemberChangeDTO;
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
        Member member = memberDTO.toEntity();
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

    @Override
    public void delete(MemberDTO memberDTO){
        String email = memberDTO.getEmail();
        TypedQuery<Member> memberTypedQuery = em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email);

        Member member = memberTypedQuery.getSingleResult();
        em.remove(member);
    }


    @Override
    public boolean changeDBPwd(MemberChangeDTO memberChangeDTO) {
        String email = memberChangeDTO.getEmail();

        TypedQuery<Member> memberTypedQuery = em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email);

        //멤버 객체 찾지 못했을 경우의 처리 필요
        List<Member> resultList = memberTypedQuery.getResultList();

        if(resultList.size() != 0) {
            Member member = resultList.get(0);
            MemberDTO memberDTO = new MemberDTO(member.getNick(),
                    member.getEmail(),
                    member.getPassword(),
                    member.getArea());
            memberDTO.setPw(memberChangeDTO.getNew_pw());
            save(memberDTO);

            String c_email = memberDTO.getEmail();
            TypedQuery<Member> c_memberTypedQuery = em.createQuery("select m from Member m where m.email = :email", Member.class)
                    .setParameter("email", c_email);

            List<Member> c_resultList = memberTypedQuery.getResultList();
            if(c_resultList.size() != 0){
                Member c_member = c_resultList.get(0);
                if (c_member.getPassword() == memberChangeDTO.getNew_pw()) {
                    return true;   // 비밀번호 변경 완료
                }
                else{
                    return false;  // 비밀번호 변경 실패
                }
            }
        }
        else{
            return false;
        }
        return false;
    }
}
