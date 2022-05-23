package footprints.footprints.domain;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    private Long user_id;
    private String email;
    private String pw;
    private String nick;
    private String phone;
    private String area;

    @Builder
    public Member(String email, String pw, String nick, String phone, String area){
        this.email = email;
        this.pw = pw;
        this.nick = nick;
        this.phone = phone;
        this.area = area;
    }

}
