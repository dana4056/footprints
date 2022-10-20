package footprints.footprints.controller.post;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostDTO;
import footprints.footprints.repository.post.PostRepository;
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

    // 리스트뷰
    @GetMapping(value = "/delivery/post")
    public ResponseEntity<List<Post>> deliveryListView(@RequestParam String area, @RequestParam int isFrontReq){
        List<Post> postList = postService.getPostList(area);

        if(isFrontReq == 1){
            if(postList.size() == 0){
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(postList, HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // 카테고리에서 선택한 종류에 대한 리스트뷰 뿌려주기
    @GetMapping(value = "/delivery/post/sort")
    public ResponseEntity<List<Post>> listOfCategory(@RequestParam String category, @RequestParam String sort_criteria, @RequestParam String area) {
        log.info("카테고리별 sorting 진입");
        List<Post> categoryList = postService.getSortingList(category, sort_criteria, area);
        if(categoryList == null){
            log.info("카테고리 리스트 널 값 반환");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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

        Post post = postRepository.findDetail(post_id);
        Member member = post.getMember();
        roomInfoService.join(member.getNick(), post.getPost_id());

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    // 글 수정하기
    @PatchMapping(value = "/delivery/post")
    public ResponseEntity<String> deliveryAmendPost(@RequestBody PostDTO postDTO){
        postService.update(postDTO);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //게시물 삭제
    @DeleteMapping(value = "/delivery/post")
    public ResponseEntity<String> deletePost(@RequestParam Long post_id){
        postService.remove(post_id);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    // 상세페이지
    @GetMapping(value = "/delivery/post/{post_id}")
    public ResponseEntity<Post> deliveryDetailPage(@PathVariable Long post_id, @RequestParam int isFrontReq){
        Post post = postService.getPost(post_id);
        postService.plusView(post); //조회수 증가

        if(isFrontReq == 1){
            return new ResponseEntity<>(post, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}

