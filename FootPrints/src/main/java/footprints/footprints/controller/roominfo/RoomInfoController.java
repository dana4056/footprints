package footprints.footprints.controller.roominfo;


import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.roomInfo.RoomInfoDTO;
import footprints.footprints.repository.post.PostRepository;
import footprints.footprints.service.roominfo.RoomInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RoomInfoController {

    private RoomInfoService roomInfoService;

    private PostRepository postRepository;

    @PostMapping(value = "roominfo/delete")
    public ResponseEntity<String> deleteRoomInfo(@RequestBody Long post_id){
        Post post = postRepository.findDetail(post_id);
        RoomInfoDTO roomInfoDTO = new RoomInfoDTO(post.getMember(), post);
        roomInfoService.remove(roomInfoDTO);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
}
