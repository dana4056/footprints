package footprints.footprints.controller.chat;

import footprints.footprints.domain.chat.ChatDataDTO;
import footprints.footprints.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping(value = "/chat/get-postidlist") // 사용자가 속한 한 post_id를 리스트(Integer) 형태로 가져온다.
    public ResponseEntity<List<Long>> getPostIdList(@RequestBody String nick){
        log.info("-------------------PostIdlist--{}",nick );
        List<Long> chatList = chatService.getList(nick);
        log.info("-------------------PostIdlist--{}",nick );
        return new ResponseEntity<List<Long>>(chatList, HttpStatus.OK);
    }

    @GetMapping(value = "/chat/get-PostInfoList") // 각 방들의 post_name과 category post_id를 리스트(String) 형태로 가져온다.
    public ResponseEntity<List<String>> getPostInfoList(@RequestBody List<Long> postIdList){

        List<String> postInfoList = chatService.getPostInfoList(postIdList);

        return new ResponseEntity<List<String>>(postInfoList, HttpStatus.OK);
    }

    @GetMapping(value = "/chat/get-NickList") // 그 방에 속한 사용자들의 nick을 리스트(String) 형태로 가져온다.
    public ResponseEntity<List<String>> getNickList(@RequestBody Long post_id){

        List<String> nickList = chatService.getNickList(post_id);

        return new ResponseEntity<List<String>>(nickList, HttpStatus.OK);
    }

    @GetMapping(value = "/chat/get-ChatList") // 채팅 기록 (from_name, time, message)를 리스트(String) 형태로 가져온다.
    public ResponseEntity<List<String>> getChatList(@RequestBody Long post_id){

        List<String> chatList = chatService.getChatList(post_id);

        return new ResponseEntity<List<String>>(chatList, HttpStatus.OK);
    }

    @PostMapping(value = "/chat/post-ChatDataList") // chat_data 테이블에 row를 추가한다.
    public ResponseEntity<String> postChatDataList(@RequestBody ChatDataDTO chatDataDTO){
        chatService.save(chatDataDTO);
        return  new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }


}
