package footprints.footprints.service.roomInfo;

import footprints.footprints.domain.post.PostDTO;
import footprints.footprints.domain.roomInfo.RoomInfoDTO;

public interface RoomInfoService {

    void join(RoomInfoDTO roomInfoDTODTO);

    void remove(RoomInfoDTO roomInfoDTODTO);
}