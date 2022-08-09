package footprints.footprints.domain.member.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//DTO 명명법 -> Res~DTO | Req~DTO
@AllArgsConstructor
@Getter
@Setter
public class ResLoginedMemberDTO {  //MemberController fetchMember 리턴값
    private String nick;
    private String area;
}
