package footprints.footprints.repository.notice;

import footprints.footprints.domain.notice.Notice;
import footprints.footprints.domain.notice.NoticeDTO;

import java.util.List;

public interface NoticeRepository {

    void save_d(NoticeDTO noticeDTO);

    void save(Notice notice);

    List<Notice> findAll();

    Notice findDetail(Long id);

    void delete(Notice notice);
}
