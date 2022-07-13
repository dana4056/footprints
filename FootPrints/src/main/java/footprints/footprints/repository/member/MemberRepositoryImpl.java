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
        Member member = memberDTO.toEntity();
        em.persist(member);
        log.info("--------saveSuccess-----------");
    }

    @Override
    public void save1(Member member){
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
//        Member member = memberDTO.toEntity();
        em.remove(findByEmail(memberDTO.getEmail()));
        log.info("--------deleteSuccess-----------");
    }


    @Override
    public boolean changeDBPwd(MemberDTO memberDTO) {
        String email = memberDTO.getEmail();

        TypedQuery<Member> memberTypedQuery = em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email);

        List<Member> resultList = memberTypedQuery.getResultList();

        if(resultList.size() != 0) {
            Member member = resultList.get(0); //DB의 실 객체 가져옴
            MemberDTO c_memberDTO = new MemberDTO(member.getNick(),
                    member.getEmail(),
                    member.getPassword(),
                    member.getArea());        // DB의 실 객체를 DTO 형태로 만들고
            delete(c_memberDTO);                // 실객체 삭제하고

            c_memberDTO.setPw(memberDTO.getPw());  // DTO 객체의 PW 바꾸고
            save(c_memberDTO);                       // 바꾼 DTO 객체를 다시 저장

            String c_email = memberDTO.getEmail();

            TypedQuery<Member> c_memberTypedQuery = em.createQuery("select m from Member m where m.email = :email", Member.class)
                    .setParameter("email", c_email);

            List<Member> c_resultList = c_memberTypedQuery.getResultList();
            if(c_resultList.size() != 0){
                Member c_member = c_resultList.get(0);
                if(c_member.getPassword().equals(memberDTO.getPw())){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }
}
