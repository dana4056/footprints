package footprints.footprints.controller.post;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostDTO;
import footprints.footprints.service.post.PostServiceImpl;
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

    // 배달 게시물 작성
    @PostMapping(value = "/delivery/post/create")
    public ResponseEntity<String> post(@RequestBody PostDTO postDTO){
        postService.join(postDTO);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }


    // 리스트뷰
    @GetMapping(value = "/delivery/post")
    public ResponseEntity<List<Post>> deliveryListView(Authentication authentication){
        Member member = (Member) authentication.getPrincipal();
        List<Post> postList = postService.getPostList(member.getArea());
        return new ResponseEntity<List<Post>>(postList, HttpStatus.OK);
    }



    // 상세페이지
    @GetMapping(value = "/delivery/post/{post_id}")
    public ResponseEntity<Post> deliveryDetailPage(@PathVariable Long post_id){
        Post post = postService.getPost(post_id);
        postService.plusView(post); //조회수 증가
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }


    @PostMapping(value = "/delivery/post/{post_id}/update")
    public ResponseEntity<String> update(@RequestBody Long post_id, PostDTO postDTO){
        log.info("--------Id:{}", postDTO.getPost_name());
        log.info("--------Id:{}", postDTO.getCategory());
        postService.update(post_id, postDTO);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    // 카테고리에서 선택한 종류에 대한 리스트뷰 뿌려주기
    @PostMapping(value = "/delivery/post/sort_category")
    public ResponseEntity<List<Post>> listOfCategory(@RequestBody String category, Authentication authentication) {
        log.info("카테고리 진입");
        Member member = (Member) authentication.getPrincipal();
        List<Post> categoryList = postService.getCategoryList(category, member.getArea());

        if(categoryList == null){
            log.info("카테고리 리스트 널 값 반환");
            return new ResponseEntity<List<Post>>((List<Post>) null, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<Post>>(categoryList, HttpStatus.OK);
        }
    }

    // 시간 순서에 대한 리스트뷰 뿌려주기
    @GetMapping(value = "/delivery/post/sort_time")
    public ResponseEntity<List<Post>> listOfTime(@RequestBody String time, Authentication authentication) {
        Member member = (Member) authentication.getPrincipal();
        List<Post> timeList = postService.getSortTimeList(time, member.getArea());

        return new ResponseEntity<List<Post>>(timeList, HttpStatus.OK);
    }
    // 시간 순서에 대한 리스트뷰 뿌려주기
    @GetMapping(value = "/delivery/post/sort_area")
    public ResponseEntity<List<Post>> listOfArea(@RequestBody String areaName) {
        log.info("=============/delivery/post/sort_area 진입");
        log.info("============= sort area : {}", areaName);
        List<Post> postList = postService.getPostList(areaName);
        log.info("{}", postList);
        return new ResponseEntity<List<Post>>(postList, HttpStatus.OK);
    }

}

