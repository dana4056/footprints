package footprints.footprints.domain.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomListNCompareDTO {

    private long post_id;
    private String post_name;
    private String last_message;
    private String member_nick;
    private String category;

}
