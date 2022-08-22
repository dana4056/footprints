package footprints.footprints.repository.roominfo;

import footprints.footprints.domain.roomInfo.RoomInfoDTO;

public interface RoomInfoRepository {

    void save(RoomInfoDTO roomInfoDTO);

    void delete(RoomInfoDTO roomInfoDTO);
}
