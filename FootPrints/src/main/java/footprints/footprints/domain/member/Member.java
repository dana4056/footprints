package footprints.footprints.domain.member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    private Long user_id;
    private String nick;
    private String email;
    private String pw;
    private String area;

    @Builder
    public Member(String nick, String email, String pw, String area){
        this.nick = nick;
        this.email = email;
        this.pw = pw;
        this.area = area;
    }


}
