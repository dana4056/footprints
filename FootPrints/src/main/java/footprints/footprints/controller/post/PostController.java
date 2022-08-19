package footprints.footprints.controller.post;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostDTO;
import footprints.footprints.domain.post.SortDTO;
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
    @PostMapping(value = "/delivery/post")
    public ResponseEntity<List<Post>> deliveryListView(@RequestBody String area, Authentication authentication){
        if(area.equals("")){
            return new ResponseEntity<List<Post>>((List<Post>) null, HttpStatus.UNAUTHORIZED); // 이게 안 먹어 지금
        }
        List<Post> postList = postService.getPostList(area);
        return new ResponseEntity<List<Post>>(postList, HttpStatus.OK);
    }

    // 상세페이지
    @GetMapping(value = "/delivery/post/{post_id}")
    public ResponseEntity<Post> deliveryDetailPage(@PathVariable Long post_id){
        Post post = postService.getPost(post_id);
        postService.plusView(post); //조회수 증가
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    // 이거 어디서 쓰이는거지?
    @PostMapping(value = "/delivery/post/{post_id}/update")
    public ResponseEntity<String> update(@RequestBody PostDTO postDTO){
        log.info("--------Id:{}", postDTO.getPost_name());
        log.info("--------Id:{}", postDTO.getCategory());
        postService.update(postDTO);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    // 카테고리에서 선택한 종류에 대한 리스트뷰 뿌려주기
    @PostMapping(value = "/delivery/post/sort")
    public ResponseEntity<List<Post>> listOfCategory(@RequestBody SortDTO sortDTO) {
        log.info("카테고리별 sorting 진입");
        List<Post> categoryList = postService.getSortingList(sortDTO.getCategory(), sortDTO.getSort_criteria(), sortDTO.getArea());
        if(categoryList == null){
            log.info("카테고리 리스트 널 값 반환");
            return new ResponseEntity<List<Post>>((List<Post>) null, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<Post>>(categoryList, HttpStatus.OK);
        }
    }

    // 글 수정 상세페이지
    @GetMapping(value = "/delivery/post/amend/{post_id}")
    public ResponseEntity<Post> deliveryAmendDetailPage(@PathVariable Long post_id){
        Post post = postService.getPost(post_id);
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    // 글 수정하기
    @PostMapping(value = "/delivery/post/amend")
    public ResponseEntity<String> deliveryAmendPost(@RequestBody PostDTO postDTO){
        postService.update(postDTO);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
}

