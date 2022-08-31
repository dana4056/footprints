package footprints.footprints.controller.chat;

import footprints.footprints.domain.chat.ChatDataDTO;
import footprints.footprints.service.chat.ChatService;
import footprints.footprints.domain.post.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping(value = "/chat/post-id-list") // 사용자가 속한 한 post_id를 리스트(Integer) 형태로 가져온다.
    public ResponseEntity<List<Long>> getPostIdList(@RequestParam String nick){
        List<Long> chatList = chatService.getList(nick);
        return new ResponseEntity<>(chatList, HttpStatus.OK);
    }

    @PostMapping(value = "/chat/get-PostInfoList") // 각 방들의 post_name, category post_id를 리스트(String) 형태로 가져온다.
    public ResponseEntity<List<Post>> getPostInfoList(@RequestBody List<Long> postIdList){
        log.info("-------------------getPostInfoList--{}",postIdList );
        List<Post> postInfoList = chatService.getPostInfoList(postIdList);
        return new ResponseEntity<>(postInfoList, HttpStatus.OK);
    }

    @GetMapping(value = "/chat/get-nick-list") // 그 방에 속한 사용자들의 nick -> 리스트(String) 형태로 가져온다.
    public ResponseEntity<List<String>> getNickList(@RequestParam Long post_id){
        log.info("-------------------getNickList--{}",post_id);
        List<String> nickList = chatService.getNickList(post_id);
        return new ResponseEntity<>(nickList, HttpStatus.OK);
    }

    @GetMapping(value = "/chat") // 채팅 기록 (from_name, time, message)를 리스트(String) 형태로 가져온다.
    public ResponseEntity<List<ChatDataDTO>> getChatList(@RequestParam Long post_id){
        log.info("-------------------getChatList--{}",post_id);
        List<ChatDataDTO> chatList = chatService.getChatList(post_id);
        return new ResponseEntity<>(chatList, HttpStatus.OK);
    }

    @PostMapping(value = "/chat") // chat_data 테이블에 row 추가한다.
    public ResponseEntity<String> postChatDataList(@RequestBody ChatDataDTO chatDataDTO){
        chatService.save(chatDataDTO);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @DeleteMapping(value = "/chat")
    public ResponseEntity<String> deleteChat(@RequestParam Long post_id) {
        chatService.delete(post_id);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}
