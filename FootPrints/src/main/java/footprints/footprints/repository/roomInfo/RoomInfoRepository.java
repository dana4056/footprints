package footprints.footprints.repository.roomInfo;

import footprints.footprints.domain.roomInfo.RoomInfo;
import footprints.footprints.domain.roomInfo.RoomInfoDTO;

public interface RoomInfoRepository {
    void save_d(RoomInfoDTO roomInfoDTO);
}
