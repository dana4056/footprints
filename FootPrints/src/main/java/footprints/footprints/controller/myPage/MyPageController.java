package footprints.footprints.controller.myPage;

import footprints.footprints.domain.member.DTO.MemberDTO;
import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.post.Post;
import footprints.footprints.repository.member.MemberRepository;
import footprints.footprints.service.MyPage.MyPageService;
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
public class MyPageController {

    private final MyPageService myPageService;
    private final MemberRepository memberRepository;

    @GetMapping(value = "/users/{nick}/my-post") // 마이페이지 메인페이지 접속시
    public ResponseEntity<List<Post>> getMyPostList(@PathVariable String nick){
        log.info("--------------------- getMyPostList {}", nick);
        List<Post> myPost = myPageService.getMyPost(nick);

        if(myPost == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(myPost, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/users/{nick}/attend-post") // 마이페이지 메인페이지 접속시
    public ResponseEntity<List<Post>> getAttendPostList(@PathVariable String nick){
        List<Post> attendPost = myPageService.getAttendPost(nick);

        if(attendPost == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(attendPost, HttpStatus.OK);
        }
    }

    @PatchMapping(value = "/users")  // 정보 수정
    public ResponseEntity<String> changeMyInfo(@RequestBody MemberDTO memberDTO){
        myPageService.changeInfo(memberDTO);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}


