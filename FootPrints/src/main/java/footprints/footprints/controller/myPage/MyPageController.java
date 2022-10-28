package footprints.footprints.controller.myPage;

import footprints.footprints.domain.member.DTO.MemberDTO;
import footprints.footprints.domain.post.Post;
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

    @GetMapping(value = "/users/{nick}/my-post") // 마이페이지 메인페이지 접속시
    public ResponseEntity<List<Post>> getMyPostList(@PathVariable String nick){
        log.info("--------------------- getMyPostList {}", nick);
        List<Post> myPost = myPageService.getMyPost(nick);
        return new ResponseEntity<>(myPost, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{nick}/attend-post") // 마이페이지 메인페이지 접속시
    public ResponseEntity<List<Post>> getAttendPostList(@PathVariable String nick){
        List<Post> attendPost = myPageService.getAttendPost(nick);
        return new ResponseEntity<>(attendPost, HttpStatus.OK);
    }

    @PatchMapping(value = "/users") // 마이페이지 메인페이지 접속시
    public ResponseEntity<String> changeMyInfo(@RequestBody MemberDTO memberDTO){
        myPageService.changeInfo(memberDTO);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping(value = "/users/likes-post") // 마이페이지에서 좋아요했던 게시글들 불러오기
    public ResponseEntity<List<Post>> getLikePosts(@RequestBody String nick){

        List<Post> likePost = myPageService.getLikePost(nick);

        if(likePost != null){
            return new ResponseEntity<List<Post>>(likePost, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<Post>>(likePost, HttpStatus.BAD_REQUEST);
        }



    }
    //    // 리스트뷰
//    @PostMapping(value = "/delivery/post")
//    public ResponseEntity<List<Post>> deliveryListView(@RequestBody String area, Authentication authentication){
//        if(area.equals("")){
//            return new ResponseEntity<List<Post>>((List<Post>) null, HttpStatus.UNAUTHORIZED); // 이게 안 먹어 지금
//        }
//        List<Post> postList = postService.getPostList(area);
//        return new ResponseEntity<List<Post>>(postList, HttpStatus.OK);
//    }
}


