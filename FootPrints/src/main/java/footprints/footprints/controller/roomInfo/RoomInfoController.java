package footprints.footprints.controller.roomInfo;


import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.roomInfo.JoinDTO;
import footprints.footprints.domain.roomInfo.RoomInfoDTO;
import footprints.footprints.repository.post.PostRepository;
import footprints.footprints.service.roomInfo.RoomInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RoomInfoController {

    private final RoomInfoService roomInfoService;
    private final PostRepository postRepository;


    @PostMapping(value = "/room-info")
    public ResponseEntity<String> joinRoom(@RequestBody JoinDTO joinDTO){
        String nick = joinDTO.getNick();
        Long post_id = joinDTO.getPost_id();
        roomInfoService.join(nick, post_id);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    // 방 정보 수정
    @PatchMapping(value = "/room-info")
    public ResponseEntity<String> exitRoom(@RequestParam String nick, @RequestParam Long post_id) {
        roomInfoService.exit(nick, post_id);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @DeleteMapping(value = "/room-info")
    public ResponseEntity<String> deleteRoomInfo(@RequestParam Long post_id){
        Post post = postRepository.findDetail(post_id);
        roomInfoService.delete(post.getPost_id());
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}
