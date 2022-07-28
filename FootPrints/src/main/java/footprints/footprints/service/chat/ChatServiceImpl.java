package footprints.footprints.service.chat;

import footprints.footprints.domain.chat.ChatData;
import footprints.footprints.domain.chat.ChatDataDTO;
import footprints.footprints.repository.chat.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Override
    public List<Integer> getList(String nick) {
        List<Integer> postIdList = chatRepository.getPostIdList(nick);

        return postIdList;
    }

    @Override
    public List<String> getPostInfoList(List<Long> postIdList) {
        List<String> postInfoList = chatRepository.getPostInfoList(postIdList);

        return postInfoList;
    }

    @Override
    public List<String> getNickList(Long post_id) {
        List<String> nickList = chatRepository.getNickList(post_id);

        return nickList;
    }

    @Override
    public List<String> getChatList(Long post_id) {
        List<String> chatList = chatRepository.getChatList(post_id);

        return chatList;
    }

    @Override
    public void save(ChatDataDTO chatDataDTO) {
        chatRepository.save(chatDataDTO);
    }
}
