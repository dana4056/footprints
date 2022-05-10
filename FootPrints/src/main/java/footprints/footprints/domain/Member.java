package footprints.footprints.domain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
//    @Column(name = "member_id")
    private Long user_id;
    private String email;
    private String pw;
    private String nick;
    private String phone;
    private String area;

    public Member(String email, String pw, String nick, String phone, String area){
        this.email = email;
        this.pw = pw;
        this.nick = nick;
        this.phone = phone;
        this.area = area;
    }

    public Member() {

    }
}
