package footprints.footprints.controller.post;

import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostDTO;
import footprints.footprints.domain.post.SortDTO;
import footprints.footprints.domain.roomInfo.RoomInfoDTO;
import footprints.footprints.repository.post.PostRepository;
import footprints.footprints.service.post.PostServiceImpl;
import footprints.footprints.service.roomInfo.RoomInfoService;
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
    private final PostRepository postRepository;
    private final RoomInfoService roomInfoService;

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
    public ResponseEntity<List<Post>> listOfCategory(@RequestParam SortDTO sortDTO) {
        log.info("카테고리별 sorting 진입");
        List<Post> categoryList = postService.getSortingList(sortDTO.getCategory(), sortDTO.getSort_criteria(), sortDTO.getArea());
        if(categoryList == null){
            log.info("카테고리 리스트 널 값 반환");
            return new ResponseEntity<>((List<Post>) null, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(categoryList, HttpStatus.OK);
        }
    }

    // 배달 게시물 작성, 게시물 작성시 room_info -> row 추가
    @PostMapping(value = "/delivery/post")
    public ResponseEntity<String> post(@RequestBody PostDTO postDTO){
        postService.join(postDTO);

        //post 추가 시 room_info 테이블에 row 추가
        Post post = postRepository.findDetail(postDTO.getPost_id());
        RoomInfoDTO roomInfoDTO = new RoomInfoDTO(post.getMember(), post);
        roomInfoService.join1(roomInfoDTO);

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    // 글 수정하기
    @PatchMapping(value = "/delivery/post")
    public ResponseEntity<String> deliveryAmendPost(@RequestBody PostDTO postDTO){
        postService.update(postDTO);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //게시물 삭제
    @DeleteMapping(value = "“/delivery/post”")
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
}

