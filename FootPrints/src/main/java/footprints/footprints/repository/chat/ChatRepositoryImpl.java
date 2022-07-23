package footprints.footprints.repository.chat;

import footprints.footprints.repository.member.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatRepositoryImpl implements ChatRepository {

    private final MemberRepositoryImpl memberRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Integer> getPostIdList(String nick) {
        TypedQuery<Integer> integerTypedQuery = em.createQuery(“select r.post_id
                                                                from room_info r
                                                                where r.nick = :nick”, Integer.class).setParameter(“nick”, nick);
        List<Integer> resultList = integerTypedQuery.getResultList();

        if (resultList.size() == 0) return null;

        else return resultList.get(0);
    }

    @Override
    public List<String> getPostInfoList(List<Integer> postIdList) {
        TypedQuery<String> stringTypedQuery = em.createQuery(“select p.post_name, p.category
                                                                from post p
                                                                where p.post_id in :list”, String.class).setParameter(“list”, list);

        List<String> resultList = stringTypedQuery.getResultList();

        if (resultList.size() == 0) return null;

        else return resultList.get(0);
    }

    @Override
    public List<String> getNickList(int post_id) {
        TypedQuery<String> stringTypedQuery = em.createQuery(“select r.nick
                                                                from room_info r
                                                                where r.post_id = :post_id”, String.class).setParameter(“post_id”, post_id);
        List<String> resultList = stringTypedQuery.getResultList();

        if (resultList.size() == 0) return null;

        else return resultList.get(0);
    }

    @Override
    public List<String> getChatList(int post_id) {
        TypedQuery<String> stringTypedQuery = em.createQuery(“select c.from_name, c.time, c.message
                                                                from chat_data c
                                                                where c.post_id = :post_id”, String.class).setParameter(“post_id”, post_id);
        List<String> resultList = stringTypedQuery.getResultList();

        if (resultList.size() == 0) return null;

        else return resultList.get(0);
    }
}
