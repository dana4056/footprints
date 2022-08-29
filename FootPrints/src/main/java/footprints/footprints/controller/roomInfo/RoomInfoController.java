package footprints.footprints.controller.roomInfo;


import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.roomInfo.RoomInfoDTO;
import footprints.footprints.repository.post.PostRepository;
import footprints.footprints.service.roomInfo.RoomInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RoomInfoController {

    private RoomInfoService roomInfoService;

    private PostRepository postRepository;

    @PostMapping(value = "/roomInfo/join/{nick}/{post_id}")
    public ResponseEntity<String> joinRoom(@PathVariable String nick, @PathVariable Long post_id){
        roomInfoService.join(nick, post_id);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    @PostMapping(value = "/roomInfo/exit/{nick}/{post_id}")
    public ResponseEntity<String> exitRoom(@PathVariable String nick, @PathVariable Long post_id) {
        roomInfoService.exit(nick, post_id);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    @PostMapping(value = "/roomInfo/delete")
    public ResponseEntity<String> deleteRoomInfo1(@RequestBody Long post_id) {
        roomInfoService.delete(post_id);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
}
