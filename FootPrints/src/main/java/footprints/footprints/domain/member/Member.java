package footprints.footprints.domain.member;
import footprints.footprints.domain.post.Post;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter @Setter
@ToString(exclude = "posts")
public class Member {

    @Id
    @GeneratedValue
    private Long user_id;
    private String nick;
    private String email;
    private String pw;
    private String area;

    @OneToMany(mappedBy="member",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String nick, String email, String pw, String area){
        this.nick = nick;
        this.email = email;
        this.pw = pw;
        this.area = area;
    }


}
