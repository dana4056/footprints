package footprints.footprints.service.chat;

import java.util.List;

public interface ChatService {
    List<Integer> getList(String nick);

    List<String> getPostInfoList(List<Integer> postIdList);

    List<String> getNickList(int post_id);

    List<String> getChatList(int post_id);
}
