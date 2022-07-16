package footprints.footprints.repository.post;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostDTO;
import footprints.footprints.repository.member.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository{

    private final MemberRepositoryImpl memberRepository;

    @PersistenceContext
    private EntityManager em;


    @Override
    public void save_d(PostDTO postDTO){
        Member member = memberRepository.findByNick(postDTO.getNick());
        Post post = new Post(postDTO.getPost_name(), postDTO.getPost_content(), postDTO.getCategory(), postDTO.getTake_loc(), postDTO.getParticipant_num(), postDTO.getMax_person_num(), postDTO.getValid_time(), postDTO.getView_num(), member);
        log.info("PostRepositoryImpl-post: {}",post);
        if(post.getPost_id() == null){
            log.info("-------------------------------------------");
            log.info("id 없음");
            log.info("-------------------------------------------");
            em.persist(post);
        }
        else{
            log.info("-------------------------------------------");
            log.info("id 있음");
            log.info("-------------------------------------------");
            em.merge(post);
        }

    }

    @Override
    public void save(PostDTO postDTO){
        Post post = postDTO.toEntity();
        if(post.getPost_id() == null){
            log.info("-------------------------------------------");
            log.info("id 없음");
            log.info("-------------------------------------------");
            em.persist(post);
        }
        else{
            log.info("-------------------------------------------");
            log.info("id 있음");
            log.info("-------------------------------------------");
            em.merge(post);
        }
    }

    @Override
    public void save1(Post post){
        if(post.getPost_id() == null){
            log.info("-------------------------------------------");
            log.info("id 없음");
            log.info("-------------------------------------------");
            em.persist(post);
        }
        else{
            log.info("-------------------------------------------");
            log.info("id 있음");
            log.info("-------------------------------------------");
            em.merge(post);
        }
    }

    @Override
    public List<Post> findAll(String area_name) {
        TypedQuery<Post> sameArea = em.createQuery("select p from Post p where p.area_name = :area_name",
                Post.class).setParameter("area_name", area_name);

        List<Post> resultList = sameArea.getResultList();

        return resultList;
    }

    @Override
    public Post findDetail(Long post_num) {
        return em.find(Post.class, post_num);
    }

    @Override
    public List<Post> findCategory(String category, String areaName) {
        TypedQuery<Post> sameCategory = em.createQuery("select p from Post p where p.user_area = :areaName " +
                        "and p.category = :category order by p.valid_time",
                Post.class).setParameter("areaName", areaName).setParameter("category", category);

        List<Post> resultList = sameCategory.getResultList();

        return resultList;
    }


}
