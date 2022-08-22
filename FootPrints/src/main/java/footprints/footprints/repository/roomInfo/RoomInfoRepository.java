package footprints.footprints.repository.roomInfo;

public interface RoomInfoRepository {
    void save_d(String nick, Long post_id);
    void delete_d(String nick, Long post_id);
    void delete_all(Long post_id);
}
