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

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostServiceImpl postService;

    @PostMapping(value = "/post")
    public ResponseEntity<String> post(@RequestBody PostDTO postDTO){
        log.info("--------Id:{}", postDTO.getPost_name());
        log.info("--------Id:{}", postDTO.getCategory());
        postService.join(postDTO);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<String> update(@RequestBody PostDTO postDTO){
        log.info("--------Id:{}", postDTO.getPost_name());
        log.info("--------Id:{}", postDTO.getCategory());
        postService.update(postDTO);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    //리스트뷰 페이지로 이동
    @GetMapping(value = "/listViewPage")
    public ResponseEntity<List<Post>> listView(@RequestBody String areaName){
        List<Post> postList = postService.getPostList(areaName);

        return new ResponseEntity<List<Post>>(postList, HttpStatus.OK);
    }

    // 상세페이지로 이동
    @GetMapping(value = "/detailPage")
    public ResponseEntity<Post> detailPage(@RequestBody Long post_num){
        Post post = postService.getPost(post_num);

        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }
}

