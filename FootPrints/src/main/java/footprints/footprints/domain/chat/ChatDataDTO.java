package footprints.footprints.domain.chat;

import footprints.footprints.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ChatDataDTO {

    private String from_name;

    private String time;

    private String message;

    private Long post_id;

    public ChatDataDTO(ChatData chatData) {
        this.from_name = chatData.getFrom_name();
        this.time = chatData.getTime();
        this.message = chatData.getMessage();
    }


//    public ChatData toEntity() {
//        return ChatData.builder()
//                .from_name(from_name)
//                .time(time)
//                .message(message)
//                .build();
//    }

}
