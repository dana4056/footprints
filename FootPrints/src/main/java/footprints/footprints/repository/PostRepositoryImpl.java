package footprints.footprints.repository;

import footprints.footprints.domain.Post;
import footprints.footprints.domain.PostDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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


}
