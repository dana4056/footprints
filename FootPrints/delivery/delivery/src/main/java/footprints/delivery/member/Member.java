package footprints.delivery.member;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Member {
    private String email;
    private String pw;
    private String nick;
    private String phone;
    private String area;
}
