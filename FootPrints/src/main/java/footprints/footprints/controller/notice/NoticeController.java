package footprints.footprints.controller.notice;

import footprints.footprints.domain.notice.Notice;
import footprints.footprints.domain.notice.NoticeDTO;
import footprints.footprints.service.notice.NoticeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final NoticeServiceImpl noticeService;

    // 공지사항 리스트뷰
    @GetMapping(value = "/notice")
    public ResponseEntity<List<Notice>> NoticeListView(){
        log.info("=============/notice 진입");
        List<Notice> noticeList = noticeService.getNoticeList();
        log.info("{}", noticeList);
        return new ResponseEntity<>(noticeList, HttpStatus.OK);
    }

    // 공지사항 등록
    @PostMapping("/notice/create")
    public ResponseEntity<String> CreateNotice(@RequestBody NoticeDTO noticeDTO){
        log.info("notice/create whit {}", noticeDTO);
        noticeService.join(noticeDTO);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    // 상세페이지
    @GetMapping(value = "/notice/{notice_id}")
    public ResponseEntity<Notice> NoticeDetailPage(@PathVariable("notice_id") Long notice_id){
        Notice notice = noticeService.getNotice(notice_id);
        notice.Plus_view();
//        noticeRepository.save(notice);  merge error 가 있음
        return new ResponseEntity<>(notice, HttpStatus.OK);
    }


}
