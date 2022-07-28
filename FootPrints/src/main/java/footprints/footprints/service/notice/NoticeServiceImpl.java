package footprints.footprints.service.notice;

import footprints.footprints.domain.notice.Notice;
import footprints.footprints.domain.notice.NoticeDTO;
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
    public void join(NoticeDTO noticeDTO) {
        noticeRepository.save_d(noticeDTO);
    }

}
