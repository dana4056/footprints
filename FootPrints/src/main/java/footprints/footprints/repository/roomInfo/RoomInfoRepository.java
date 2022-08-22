package footprints.footprints.repository.roomInfo;

import footprints.footprints.domain.roomInfo.RoomInfoDTO;

public interface RoomInfoRepository {

    void save(RoomInfoDTO roomInfoDTO);

    void delete(RoomInfoDTO roomInfoDTO);
}