package footprints.footprints.service.notice;

import footprints.footprints.domain.notice.Notice;
import footprints.footprints.domain.notice.NoticeDTO;
import footprints.footprints.domain.notice.NoticeDTO_cID;
import footprints.footprints.domain.post.Post;

import java.util.List;

public interface NoticeService {

    List<Notice> getNoticeList(); // 전체 공지사항 가져오기

    Notice getNotice(Long id); // 상세 페이지

    void plusView(Notice notice);

    void join(NoticeDTO noticeDTO);

    void update(NoticeDTO_cID noticeDTO_cID);

    void remove(Long id);
}
