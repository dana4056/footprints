package footprints.footprints.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MemberChangeDTO {
    private String nick;
    private String email;
    private String pw;
    private String area;
    private String new_pw;
}