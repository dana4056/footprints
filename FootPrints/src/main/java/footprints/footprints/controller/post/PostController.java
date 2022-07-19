package footprints.footprints.controller.post;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostDTO;
import footprints.footprints.repository.post.PostRepository;
import footprints.footprints.service.post.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostServiceImpl postService;
    private final PostRepository postRepository;

    // 배달 게시물 작성
    @PostMapping(value = "/delivery/post/create")
    public ResponseEntity<String> post(@RequestBody PostDTO postDTO){
        postService.join(postDTO);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    // 리스트뷰
    @GetMapping(value = "/delivery/post")
    public ResponseEntity<List<Post>> detailPage(Authentication authentication){
        log.info("=============/delivery/post 진입");
        Member member = (Member) authentication.getPrincipal();
        log.info("============= member area : {}",member.getArea());
        List<Post> postList = postService.getPostList(member.getArea());
        log.info("{}", postList);
        return new ResponseEntity<List<Post>>(postList, HttpStatus.OK);
    }


    // 상세페이지
    @GetMapping(value = "/delivery/post/{post_id}")
    public Post detailPage(@PathVariable Long post_id){
        Post post = postService.getPost(post_id);
        post.Plus_view();
        postRepository.save(post);
        return post;
    }


    @PostMapping(value = "/delivery/post/{post_id}/update")
    public ResponseEntity<String> update(@RequestBody Long post_id, PostDTO postDTO){
        log.info("--------Id:{}", postDTO.getPost_name());
        log.info("--------Id:{}", postDTO.getCategory());
        postService.update(post_id, postDTO);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    // 카테고리에서 선택한 종류에 대한 리스트뷰 뿌려주기
    @GetMapping(value = "/category")
    public ResponseEntity<List<Post>> listOfCategory(@RequestBody String category, String areaName) {
        List<Post> categoryList = postService.getCategoryList(category, areaName);

        return new ResponseEntity<List<Post>>(categoryList, HttpStatus.OK);
    }
    // 시간 순서에 대한 리스트뷰 뿌려주기
    @GetMapping(value = "/sort_time")
    public ResponseEntity<List<Post>> listOfTime(@RequestBody String time, String areaName) {
        List<Post> timeList = postService.getSortTimeList(time, areaName);

        return new ResponseEntity<List<Post>>(timeList, HttpStatus.OK);
    }

}

