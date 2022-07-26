package footprints.footprints.domain.chat;

import footprints.footprints.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@Getter @Setter
public class ChatDataDTO {

    private String from_name;

    private String time;

    private String message;

    private Long post_id;

    public ChatDataDTO(ChatData chatData) {
        this.from_name = from_name;
        this.time = time;
        this.message = message;
    }


    public ChatData toEntity() {
        return ChatData.builder()
                .from_name(from_name)
                .time(time)
                .message(message)
                .build();
    }

}