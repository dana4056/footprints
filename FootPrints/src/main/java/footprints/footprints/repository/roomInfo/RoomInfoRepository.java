package footprints.footprints.repository.roomInfo;

import footprints.footprints.domain.roomInfo.RoomInfoDTO;

public interface RoomInfoRepository {

    void save(RoomInfoDTO roomInfoDTO);

    void delete(RoomInfoDTO roomInfoDTO);

    void save_d(String nick, Long post_id);
    void delete_d(String nick, Long post_id);
    void delete_all(Long post_id);
}