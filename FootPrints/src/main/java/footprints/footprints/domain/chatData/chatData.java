package footprints.footprints.domain.chatData;


import footprints.footprints.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class chatData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chat_id;

    private String from_name;

    private String time;

    private String message;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="POST_ID")
    private Post post;

}
