package footprints.footprints.service.chat;

import footprints.footprints.domain.chat.ChatData;
import footprints.footprints.domain.chat.ChatDataDTO;

import java.util.List;

public interface ChatService {
    List<Integer> getList(String nick);

    List<String> getPostInfoList(List<Integer> postIdList);

    List<String> getNickList(int post_id);

    List<String> getChatList(int post_id);

    void save(ChatDataDTO chatDataDTO);
}