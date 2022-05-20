package footprints.delivery.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
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

}
