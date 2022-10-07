package footprints.footprints.service.roomInfo;

import footprints.footprints.domain.post.PostDTO;
import footprints.footprints.domain.roomInfo.RoomInfoDTO;

public interface RoomInfoService {

    void join(String nick, Long post_id);

    void join_add(String nick, Long post_id);

    void exit(String nick, Long post_id);

    void delete(Long post_id);
}