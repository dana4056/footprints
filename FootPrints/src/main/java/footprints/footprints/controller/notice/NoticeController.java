package footprints.footprints.controller.notice;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.notice.Notice;
import footprints.footprints.domain.post.Post;
import footprints.footprints.repository.notice.NoticeRepository;
import footprints.footprints.service.notice.NoticeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final NoticeServiceImpl noticeService;
    private final NoticeRepository noticeRepository;

    // 리스트뷰
    @GetMapping(value = "/notice")
    public ResponseEntity<List<Notice>> NoticeListView(){
        log.info("=============/notice 진입");
        List<Notice> noticeList = noticeService.getNoticeList();
        log.info("{}", noticeList);
        return new ResponseEntity<List<Notice>>(noticeList, HttpStatus.OK);
    }


    // 상세페이지
    @GetMapping(value = "/notice/{notice_id}")
    public Notice NoticeDetailPage(@PathVariable Long notice_id){
        Notice notice = noticeService.getNotice(notice_id);
        notice.Plus_view();
        noticeRepository.save(notice);
        return notice;
    }
}
