package footprints.footprints.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class MemberDTO {
    private String nick;
    private String email;
    private String pw;
    private String area;
}
