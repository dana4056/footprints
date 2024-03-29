package footprints.footprints.service.notice;

import footprints.footprints.domain.notice.Notice;
import footprints.footprints.domain.notice.NoticeDTO;
import footprints.footprints.domain.notice.NoticeDTO_cID;
import footprints.footprints.domain.post.Post;
import footprints.footprints.repository.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

    private final NoticeRepository noticeRepository;

    @Override
    public List<Notice> getNoticeList() {
        List<Notice> notices = noticeRepository.findAll();
        return notices;
    }

    @Override
    public Notice getNotice(Long id) {
        Notice detail = noticeRepository.findDetail(id);
        return detail;
    }

    @Override
    public void plusView(Notice notice) {
        notice.Plus_view();
        noticeRepository.save(notice);
    }

    @Override
    public void join(NoticeDTO noticeDTO) {
        noticeRepository.save_d(noticeDTO);
    }

    @Override
    public void update(NoticeDTO_cID noticeDTO_cID) {
        Notice notice = noticeRepository.findDetail(noticeDTO_cID.getId());
        notice.Update(noticeDTO_cID);
        noticeRepository.save(notice);
    }

    @Override
    public void remove(Long id){
        Notice notice = noticeRepository.findDetail(id);
        noticeRepository.delete(notice);
    }

}
