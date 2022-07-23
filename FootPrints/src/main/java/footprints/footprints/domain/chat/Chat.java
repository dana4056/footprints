package footprints.footprints.domain.chat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.security.Timestamp;

public class Chat {
    @Id
    @GeneratedValue
    private Long message_id; // 하나의 메세지의 고유 번호(pk)
//    private Long room_id; // 채팅방의 번호

    private String from_name; // 메세지의 주인
    private Timestamp send_at; // 메세지 전송시간
    private String message; // 메세지 내용
}
