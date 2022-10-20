package footprints.footprints.controller.notice;

import footprints.footprints.domain.notice.Notice;
import footprints.footprints.domain.notice.NoticeDTO;
import footprints.footprints.domain.notice.NoticeDTO_cID;
import footprints.footprints.service.notice.NoticeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return new ResponseEntity<>(noticeList, HttpStatus.OK);
    }

    // 공지사항 등록
//    @PreAuthorize("isAuthenticated() and (( #user.name == principal.name ) or hasRole('ROLE_ADMIN'))")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value ="/notice")
    public ResponseEntity<String> CreateNotice(@RequestBody NoticeDTO noticeDTO){
        log.info("notice/create with {}", noticeDTO);
        noticeService.join(noticeDTO);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    // 공지사항 수정
    @PreAuthorize("hasRole('ROLE_USER')")
    @PatchMapping(value ="/notice")
    public ResponseEntity<String> AmendNotice(@RequestBody NoticeDTO_cID noticeDTO_cID){
        log.info("notice/update with {}", noticeDTO_cID);
        noticeService.update(noticeDTO_cID);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //게시물 삭제
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping(value = "/notice")
    public ResponseEntity<String> deleteNotice(@RequestParam Long id){
        noticeService.remove(id);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }


    // 상세페이지
    @GetMapping(value = "/notice/{notice_id}")
    public ResponseEntity<Notice> NoticeDetailPage(@PathVariable Long notice_id, @RequestParam int isFrontReq){

        Notice notice = noticeService.getNotice(notice_id);
        noticeService.plusView(notice);

        if(isFrontReq == 1){
            return new ResponseEntity<>(notice, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
