package footprints.footprints.controller.post;

import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostDTO;
import footprints.footprints.service.post.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Controller
@RestController
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
    public List<Post> detailPage(){
         List<Post> postList = postService.getPostList("성북구 정릉동");

        return postList;
    }

    // 상세페이지
    @GetMapping(value = "/delivery/post/{post_id}")
    public Post detailPage(@PathVariable Long post_id){
        Post post = postService.getPost(post_id);

        return post;
    }


    @PostMapping(value = "/delivery/post/{post_id}/update")
    public ResponseEntity<String> update(@RequestBody PostDTO postDTO){
        log.info("--------Id:{}", postDTO.getPost_name());
        log.info("--------Id:{}", postDTO.getCategory());
        postService.update(postDTO);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
}

