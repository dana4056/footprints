package footprints.footprints.repository.chat;

import footprints.footprints.domain.chat.ChatData;
import footprints.footprints.domain.chat.ChatDataDTO;
import footprints.footprints.domain.post.Post;

import java.util.List;

public interface ChatRepository {

    List<Long> getPostIdList(String nick);

    List<Post> getPostInfoList(List<Long> postIdList);

    List<String> getNickList(Long post_id);

    List<ChatDataDTO> getChatList(Long post_id);

    void save(ChatDataDTO chatDataDTO);
}
