package footprints.footprints.service.chat;

import footprints.footprints.domain.chat.ChatData;
import footprints.footprints.domain.chat.ChatDataDTO;
import footprints.footprints.domain.post.Post;
import footprints.footprints.repository.chat.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Override
    public List<Long> getList(String nick) {
        List<Long> postIdList = chatRepository.getPostIdList(nick);
        log.info("-------------------getList--{}",nick );
        return postIdList;
    }

    @Override
    public List<Post> getPostInfoList(List<Long> postIdList) {
        List<Post> postInfoList = chatRepository.getPostInfoList(postIdList);

        return postInfoList;
    }

    @Override
    public List<String> getNickList(Long post_id) {
        List<String> nickList = chatRepository.getNickList(post_id);

        return nickList;
    }

    @Override
    public List<ChatDataDTO> getChatList(Long post_id) {
        List<ChatDataDTO> chatList = chatRepository.getChatList(post_id);

        return chatList;
    }

    @Override
    public void save(ChatDataDTO chatDataDTO) {
        chatRepository.save(chatDataDTO);
    }

    public void delete(Long post_id) {
        chatRepository.delete_all(post_id);
    }
}
