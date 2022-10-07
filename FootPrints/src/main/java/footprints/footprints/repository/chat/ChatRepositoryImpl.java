package footprints.footprints.repository.chat;

import footprints.footprints.domain.chat.ChatData;
import footprints.footprints.domain.chat.ChatDataDTO;
import footprints.footprints.domain.post.Post;
import footprints.footprints.repository.member.MemberRepository;
import footprints.footprints.repository.post.PostRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.*;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ChatRepositoryImpl implements ChatRepository {

    private final PostRepositoryImpl postRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Long> getPostIdList(String nick) {
        TypedQuery<Long> integerTypedQuery = em.createQuery("select r.post.post_id from RoomInfo r " +
                "where r.member.nick = :nick", Long.class).setParameter("nick", nick);

        List<Long> resultList = integerTypedQuery.getResultList();
        log.info("---------------------{}", resultList.get(0));
        if (resultList.size() == 0) return null;

        else return resultList;
    }
    @Override
    public List<Post> getPostInfoList(List<Long> postIdList) {
        log.info("-------------------getPostInfoList--{}",postIdList);
        // 최종 결과를 저장할 변수.
        List<Post> resultList = new ArrayList<Post>();

        // postIdList 변수 안에 포함되어 있는 post 객체를 "in" 을 사용하여 찾는 코드가 오류가 나서
        // 반복문을 돌며 "=" 을 이용해서 객체를 탐색하도록 임시 변경
        for(long postId : postIdList) {
            TypedQuery<Post> stringTypedQuery2 = em.createQuery("select p from Post p " +
                    "where p.post_id = :postId", Post.class).setParameter("postId", postId);

            // post 객체 하나하나 resultList에 저장
            List<Post> result = stringTypedQuery2.getResultList();
            log.info("-------------------getPostInfoList--{}", result.get(0));
            resultList.add(result.get(0));
        }

        log.info("---------ChatRepositoryImpl - getPostInfoList-- resultList : {}",resultList);
        if (resultList.size() == 0) return null;
        else return resultList;
    }

    @Override
    public List<String> getNickList(Long post_id) {
        log.info("-------------------getNickList--{}",post_id);
        TypedQuery<String> stringTypedQuery = em.createQuery("select r.member.nick from RoomInfo r " +
                "where r.post.post_id = :post_id", String.class).setParameter("post_id", post_id);

        List<String> resultList = stringTypedQuery.getResultList();

        if (resultList.size() == 0) return null;

        else return resultList;
    }

    @Override
    public List<ChatDataDTO> getChatList(Long post_id) {
        log.info("-------------------getChatList--{}", post_id);
        TypedQuery<ChatData> stringTypedQuery = em.createQuery("select c from ChatData c " +
                "where c.post.post_id = :post_id", ChatData.class).setParameter("post_id", post_id);

        List<ChatData> resultQuery = stringTypedQuery.getResultList();

        List<ChatDataDTO> resultList = new ArrayList<ChatDataDTO>();

        for(ChatData chat : resultQuery) {
            resultList.add(new ChatDataDTO(chat));
        }

        if (resultList.size() == 0) return null;

        else return resultList;
    }

    @Override
    public void save(ChatDataDTO chatDataDTO) {
        Post post = postRepository.findByPostId(chatDataDTO.getPost_id());
        log.info("-------------------save--{}", chatDataDTO.getPost_id());
        ChatData chatData = new ChatData(chatDataDTO.getFrom_name(), chatDataDTO.getTime(), chatDataDTO.getMessage(), post);
        em.persist(chatData);
    }

    public void delete_all(Long post_id) {
        Query query = em.createQuery("delete from ChatData c where c.post.post_id = :post_id");
        query.setParameter("post_id", post_id).executeUpdate();
    }
}
