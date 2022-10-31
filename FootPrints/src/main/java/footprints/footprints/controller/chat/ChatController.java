package footprints.footprints.controller.chat;

import footprints.footprints.domain.chat.ChatDataDTO;
import footprints.footprints.domain.chat.RoomListNCompareDTO;
import footprints.footprints.service.chat.ChatService;
import footprints.footprints.domain.post.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/receive")
    @SendTo("/sub/send")
    public String sendMessage(ChatDataDTO chatDataDTO){
        return chatDataDTO.getMessage();
    }

    @GetMapping(value = "/chat/post-id-list") // 사용자가 속한 한 post_id를 리스트(Integer) 형태로 가져온다.
    public ResponseEntity<List<Long>> getPostIdList(@RequestParam String nick){
        List<Long> chatList = chatService.getList(nick);
        return new ResponseEntity<>(chatList, HttpStatus.OK);
    }

    @PostMapping(value = "/chat/get-PostInfoList") // 사용자가 참여된 채팅방들의 post_name, category post_id를 리스트(String) 형태로 가져온다.
    public ResponseEntity<List<RoomListNCompareDTO>> getPostInfoList(@RequestBody List<Long> postIdList){
        log.info("-------------------getPostInfoList--{}",postIdList );

        List<Post> postList = chatService.getPostInfoList(postIdList);

        List<RoomListNCompareDTO> roomListNCompareDTO = new ArrayList<>();
        for(int i = 0; i < postIdList.toArray().length; i++) {
            String lastChatting = chatService.getLastMsg(postIdList.get(i));
            Post cur_post = postList.get(i);
            RoomListNCompareDTO object = new RoomListNCompareDTO(cur_post.getPost_id(), cur_post.getPost_name(), lastChatting, cur_post.getMember().getNick(), cur_post.getCategory());
            roomListNCompareDTO.add(object);
        }

        return new ResponseEntity<>(roomListNCompareDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/chat/get-nick-list") // 그 방에 속한 사용자들의 nick -> 리스트(String) 형태로 가져온다.
    public ResponseEntity<List<String>> getNickList(@RequestParam Long post_id){
        log.info("-------------------getNickList id:{}",post_id);
        List<String> nickList = chatService.getNickList(post_id);
        return new ResponseEntity<>(nickList, HttpStatus.OK);
    }

    @GetMapping(value = "/chat") // 채팅 기록 (from_name, time, message)를 리스트(String) 형태로 가져온다.
    public ResponseEntity<List<ChatDataDTO>> getChatList(@RequestParam String post_id){
        log.info("-------------------getChatList id:{}",post_id);
        List<ChatDataDTO> chatList = chatService.getChatList(Long.parseLong(post_id));
        return new ResponseEntity<>(chatList, HttpStatus.OK);
    }

    @GetMapping(value = "/chat/{post_id}") // 각 채팅방의 마지막 메시지
    public ResponseEntity<String> getLastMsg(@PathVariable Long post_id){
        String lastMsg = chatService.getLastMsg(post_id);
        return new ResponseEntity<>(lastMsg, HttpStatus.OK);
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
