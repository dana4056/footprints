package footprints.footprints.notice;


import footprints.footprints.domain.notice.Notice;
import footprints.footprints.repository.notice.NoticeRepository;
import footprints.footprints.service.notice.NoticeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
class NoticeServiceTest {

    @Autowired
    NoticeRepository noticeRepository;

    @Autowired
    NoticeService noticeService;

    @Test public void save() throws Exception{
        Notice notice1 = new Notice(1L, "첫번째 공지사항", "관리자", "작성 시간", "첫번째 공지사항 내용입니다.", 0L);

        noticeRepository.save(notice1);

        Notice detailNotice = noticeRepository.findDetail(1L);

        Assertions.assertThat(notice1.getId().equals(detailNotice.getId()));
    }
}