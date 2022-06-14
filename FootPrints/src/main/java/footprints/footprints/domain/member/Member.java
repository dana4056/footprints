package footprints.footprints.domain.member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import footprints.footprints.domain.post.Post;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter @Setter
@ToString(exclude = "posts")
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String nick;
    private String email;
    private String pw;
    private String area;

    @JsonIgnore
    @OneToMany(mappedBy="member",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post){
        posts.add(post);
    }

    @Builder
    public Member(String nick, String email, String pw, String area){
        this.nick = nick;
        this.email = email;
        this.pw = pw;
        this.area = area;
    }


}
