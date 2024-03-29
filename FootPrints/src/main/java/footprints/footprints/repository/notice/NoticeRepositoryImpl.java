package footprints.footprints.repository.notice;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.notice.Notice;
import footprints.footprints.domain.notice.NoticeDTO;
import footprints.footprints.domain.post.Post;
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
public class NoticeRepositoryImpl implements NoticeRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save_d(NoticeDTO noticeDTO) {
        Notice notice = new Notice(noticeDTO.getTitle(), noticeDTO.getAuthor(), noticeDTO.getPost_time(), noticeDTO.getContent(), noticeDTO.getView_num());
        log.info("NoticeRepositoryImpl-save_d: {}",notice);
        if(notice.getId() == null){
            log.info("-------------------------------------------");
            log.info("id 없음");
            log.info("-------------------------------------------");
            em.persist(notice);
        }
        else{
            log.info("-------------------------------------------");
            log.info("id 있음");
            log.info("-------------------------------------------");
            em.merge(notice);
        }
    }

    @Override
    public void save(Notice notice) {
        log.info("NoticeRepositoryImpl-save: {}",notice);
        if(notice.getId() == null){
            log.info("-------------------------------------------");
            log.info("id 없음");
            log.info("-------------------------------------------");
            em.persist(notice);
        }
        else{
            log.info("-------------------------------------------");
            log.info("id 있음");
            log.info("-------------------------------------------");
            em.merge(notice);
        }
    }

    @Override
    public List<Notice> findAll() {
        TypedQuery<Notice> notices = em.createQuery("select n from Notice n", Notice.class);

        List<Notice> noticeList = notices.getResultList();

        return noticeList;
    }

    @Override
    public Notice findDetail(Long id) {
        return em.find(Notice.class, id);
    }

    @Override
    public void delete(Notice notice){
        if(notice.getId() == null){
            log.info("존재하지 않는 post");
        }
        else{
            em.remove(notice);
        }
    }
}
