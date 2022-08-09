package footprints.footprints.domain.member.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReqChangePwDTO { //MemberController ChangePW 요청값
    private String email;
    private String pw;
}
