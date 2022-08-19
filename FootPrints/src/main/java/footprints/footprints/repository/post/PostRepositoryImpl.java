package footprints.footprints.repository.post;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostDTO;
import footprints.footprints.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository{

    private final MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save_d(PostDTO postDTO){
        Member member = memberRepository.findByNick(postDTO.getNick());
        Post post = new Post(postDTO.getPost_id(), postDTO.getPost_name(), postDTO.getPost_content(), postDTO.getCategory(),
                postDTO.getTake_loc(), postDTO.getParticipant_num(), postDTO.getMax_person_num(), postDTO.getValid_time(),
                postDTO.getView_num(), member, postDTO.getPost_area());
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

    public void save(Post post){
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
    public Post findByPostId(Long post_id) {
        return em.find(Post.class, post_id);
    }

    @Override
    public List<Post> findAll(String area_name) {
        TypedQuery<Post> sameArea = em.createQuery("select p from Post p where p.member.area = :area_name",
                Post.class).setParameter("area_name", area_name);
        List<Post> resultList = sameArea.getResultList();
        return resultList;
    }

    @Override
    public Post findDetail(Long post_num) {
        return em.find(Post.class, post_num);
    }

    @Override
    public List<Post> findSortAll(String category, String sort_criteria, String area) {
        log.info("SORT 레파지토리에 진입 with {}, {}, {}", category, sort_criteria, area);
        if(category.equals("")){
            if(sort_criteria.equals("near")){
                TypedQuery<Post> nearTimeSort = em.createQuery("select p from Post p where p.member.area = :areaName order by p.valid_time", Post.class)
                        .setParameter("areaName", area);
                List<Post> resultList = nearTimeSort.getResultList();
                if(resultList.size() == 0){
                    return null;
                }
                else{
                    return resultList;
                }
            }
            else if(sort_criteria.equals("far")){
                TypedQuery<Post> farTimeSort = em.createQuery("select p from Post p where p.member.area = :areaName order by p.valid_time desc", Post.class)
                        .setParameter("areaName", area);
                List<Post> resultList = farTimeSort.getResultList();
                if(resultList.size() == 0){
                    return null;
                }
                else {
                    return resultList;
                }
            }
            else{
                TypedQuery<Post> defaultSort = em.createQuery("select p from Post p where p.member.area = :areaName", Post.class)
                        .setParameter("areaName", area);
                List<Post> resultList = defaultSort.getResultList();
                if(resultList.size() == 0){
                    return null;
                }
                else {
                    return resultList;
                }
            }
        }
        else{
            if(sort_criteria.equals("near")){
                TypedQuery<Post> nearTimeSort = em.createQuery("select p from Post p where p.member.area = :areaName and p.category = :category order by p.valid_time", Post.class)
                        .setParameter("areaName", area)
                        .setParameter("category", category);
                List<Post> resultList = nearTimeSort.getResultList();
                if(resultList.size() == 0){
                    return null;
                }
                else{
                    return resultList;
                }
            }
            else if(sort_criteria.equals("far")){
                TypedQuery<Post> farTimeSort = em.createQuery("select p from Post p where p.member.area = :areaName and p.category = :category order by p.valid_time desc", Post.class)
                        .setParameter("areaName", area)
                        .setParameter("category", category);
                List<Post> resultList = farTimeSort.getResultList();
                if(resultList.size() == 0){
                    return null;
                }
                else {
                    return resultList;
                }
            }
            else{
                TypedQuery<Post> defaultSort = em.createQuery("select p from Post p where p.member.area = :areaName and p.category = :category", Post.class)
                        .setParameter("areaName", area)
                        .setParameter("category", category);
                List<Post> resultList = defaultSort.getResultList();
                if(resultList.size() == 0){
                    return null;
                }
                else {
                    return resultList;
                }
            }
        }
    }
}