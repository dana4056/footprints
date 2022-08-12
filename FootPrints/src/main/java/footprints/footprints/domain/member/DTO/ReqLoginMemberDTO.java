package footprints.footprints.domain.member.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReqLoginMemberDTO { //MemberController login 요청값
    private String nick;
    private String pw;
}
