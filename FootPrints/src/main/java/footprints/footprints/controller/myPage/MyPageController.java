package footprints.footprints.controller.myPage;

import footprints.footprints.domain.member.DTO.MemberDTO;
import footprints.footprints.domain.post.Post;
import footprints.footprints.service.MyPage.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MyPageController {

    private final MyPageService myPageService;

    @PostMapping(value = "/myPage/main-myPost") // 마이페이지 메인페이지 접속시
    public ResponseEntity<List<Post>> getMyPostList(@RequestBody String nick){
        log.info("--------------------- getMyPostList {}", nick);
        List<Post> myPost = myPageService.getMyPost(nick);

        return new ResponseEntity<List<Post>>(myPost, HttpStatus.OK);
    }

    @PostMapping(value = "/myPage/main-attendPost") // 마이페이지 메인페이지 접속시
    public ResponseEntity<List<Post>> getAttendPostList(@RequestBody String nick){
        List<Post> attendPost = myPageService.getAttendPost(nick);

        return new ResponseEntity<List<Post>>(attendPost, HttpStatus.OK);
    }

    @PostMapping(value = "/myPage/changeMyInfo") // 마이페이지 메인페이지 접속시
    public ResponseEntity<String> changeMyInfo(@RequestBody MemberDTO memberDTO){
        myPageService.changeInfo(memberDTO);

        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
}


