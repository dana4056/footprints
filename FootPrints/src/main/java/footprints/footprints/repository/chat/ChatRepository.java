package footprints.footprints.repository.chat;

import footprints.footprints.domain.chat.ChatData;
import footprints.footprints.domain.chat.ChatDataDTO;

import java.util.List;

public interface ChatRepository {

    List<Long> getPostIdList(String nick);

    List<String> getPostInfoList(List<Long> postIdList);

    List<String> getNickList(Long post_id);

    List<String> getChatList(Long post_id);

    void save(ChatDataDTO chatDataDTO);
}
