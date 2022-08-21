package footprints.footprints.controller.roomInfo;

import footprints.footprints.domain.roomInfo.RoomInfoDTO;
import footprints.footprints.service.roomInfo.RoomInfoServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RoomInfoController {
    private final RoomInfoServiceImpl roomInfoService;

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
    public ResponseEntity<String> deleteRoomInfo(@RequestBody Long post_id) {
        roomInfoService.delete(post_id);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
}
