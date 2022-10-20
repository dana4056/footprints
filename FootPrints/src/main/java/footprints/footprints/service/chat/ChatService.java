package footprints.footprints.service.chat;

import footprints.footprints.domain.chat.ChatData;
import footprints.footprints.domain.chat.ChatDataDTO;
import footprints.footprints.domain.post.Post;

import java.util.List;

public interface ChatService {
    List<Long> getList(String nick);

    List<Post> getPostInfoList(List<Long> postIdList);

    List<String> getNickList(Long post_id);

    List<ChatDataDTO> getChatList(Long post_id);

    void save(ChatDataDTO chatDataDTO);

    void delete(Long post_id);

    String getLastChat(Long postId);
}
