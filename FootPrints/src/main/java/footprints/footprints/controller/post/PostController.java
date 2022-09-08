package footprints.footprints.controller.post;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostDTO;
import footprints.footprints.domain.post.SortDTO;
import footprints.footprints.domain.roomInfo.RoomInfo;
import footprints.footprints.domain.roomInfo.RoomInfoDTO;
import footprints.footprints.repository.post.PostRepository;
import footprints.footprints.service.member.MemberService;
import footprints.footprints.service.post.PostServiceImpl;
import footprints.footprints.service.roomInfo.RoomInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostServiceImpl postService;
    private final PostRepository postRepository;

    private final RoomInfoService roomInfoService;
    private final MemberService memberService;

    // 리스트뷰
    @GetMapping(value = "/delivery/post")
    public ResponseEntity<List<Post>> deliveryListView(@RequestParam String area){
//        if(area.equals("")){
//            return new ResponseEntity<List<Post>>((List<Post>) null, HttpStatus.UNAUTHORIZED); // 이게 안 먹어 지금
//        }
        List<Post> postList = postService.getPostList(area);
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    // 카테고리에서 선택한 종류에 대한 리스트뷰 뿌려주기
    @GetMapping(value = "/delivery/post/sort")
    public ResponseEntity<List<Post>> listOfCategory(@RequestParam String category, @RequestParam String sort_criteria, @RequestParam String area) {
        log.info("카테고리별 sorting 진입");
        List<Post> categoryList = postService.getSortingList(category, sort_criteria, area);
        if(categoryList == null){
            log.info("카테고리 리스트 널 값 반환");
            return new ResponseEntity<>((List<Post>) null, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(categoryList, HttpStatus.OK);
        }
    }

    // 배달 게시물 작성
    @PostMapping(value = "/delivery/post")
    public ResponseEntity<String> post(@RequestBody PostDTO postDTO){
        log.info("백 진입1");
        long post_id = postService.join(postDTO);

        if(post_id != 0) {
            Post post = postRepository.findDetail(post_id);
            Member member = post.getMember();
            roomInfoService.join(member.getNick(), post.getPost_id());

            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        }

    }

    // 글 수정하기
    @PatchMapping(value = "/delivery/post")
    public ResponseEntity<String> deliveryAmendPost(@RequestBody PostDTO postDTO){
        postService.update(postDTO);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //게시물 삭제
    @DeleteMapping(value = "/delivery/post")
    public ResponseEntity<String> deletePost1 (@RequestParam Long post_id){
        postService.remove(post_id);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    // 상세페이지
    @GetMapping(value = "/delivery/post/{post_id}")
    public ResponseEntity<Post> deliveryDetailPage(@PathVariable Long post_id){
        Post post = postService.getPost(post_id);
        postService.plusView(post); //조회수 증가
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    // 글 수정 상세페이지
    @GetMapping(value = "/delivery/post/{post_id}/edit")
    public ResponseEntity<Post> deliveryAmendDetailPage(@PathVariable Long post_id){
        Post post = postService.getPost(post_id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping(value = "/post/delete")
    public ResponseEntity<String> deletePost(@RequestBody Long post_id) {
        postService.delete(post_id);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    // 좋아요 클릭 시
    @GetMapping(value = "/post/likes")
    public ResponseEntity<String> postLikes(@PathVariable String nick, Long post_id){
        // member에 해당 게시물 post_id 추가
        memberService.like_postUpdate(nick, post_id);

        // 해당 post에 좋아요 개수 up하기
        Post post = postService.getPost(post_id);
        postService.plusLikes(post_id);

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}

