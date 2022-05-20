package footprints.footprints.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class MemberDTO {

    private String email;
    private String pw;
    private String nick;
    private String phone;
    private String area;

}
