package footprints.footprints.repository.post;

import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Slf4j
public class PostRepositoryImpl implements PostRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(PostDTO postDTO){
        Post post = postDTO.toEntity();
        em.persist(post);
    }

    @Override
    public void save1(Post post){
        em.persist(post);
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


}
