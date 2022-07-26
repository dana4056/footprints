package footprints.footprints.repository.chat;

import footprints.footprints.domain.chat.ChatData;
import footprints.footprints.domain.chat.ChatDataDTO;

import java.util.List;

public interface ChatRepository {

    List<Integer> getPostIdList(String nick);

    List<String> getPostInfoList(List<Integer> postIdList);

    List<String> getNickList(int post_id);

    List<String> getChatList(int post_id);

    void save(ChatDataDTO chatDataDTO);
}