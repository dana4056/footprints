package footprints.footprints.controller.roomInfo;

import footprints.footprints.domain.roomInfo.RoomInfo;
import footprints.footprints.domain.roomInfo.RoomInfoDTO;
import footprints.footprints.service.roomInfo.RoomInfoServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RoomInfoController {
    private final RoomInfoServiceImpl roomInfoService;

    @PostMapping(value = "/delivery/roomInfo/create")
    public ResponseEntity<String> post(@RequestBody RoomInfoDTO roomInfoDTO){
        roomInfoService.join(roomInfoDTO);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
}
