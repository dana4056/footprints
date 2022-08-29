package footprints.footprints.service.roomInfo;

import footprints.footprints.domain.post.PostDTO;
import footprints.footprints.domain.roomInfo.RoomInfoDTO;

public interface RoomInfoService {

    void join1(RoomInfoDTO roomInfoDTODTO);

    void remove(RoomInfoDTO roomInfoDTODTO);
    void join(String nick, Long post_id);

    void exit(String nick, Long post_id);

    void delete(Long post_id);
}