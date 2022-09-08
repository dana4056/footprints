package footprints.footprints.domain.chat;

import footprints.footprints.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ChatData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chat_id;

    private String from_name;

    private String time;

    private String message;

    @ManyToOne(cascade = CascadeType.PERSIST) // fetch = FetchType.LAZY,
    @JoinColumn(name="POST_ID")
    private Post post;

    @Builder
    public ChatData(String from_name, String time, String message, Post post) {
        this.from_name = from_name;
        this.time = time;
        this.message = message;
        this.post = post;
    }
}
