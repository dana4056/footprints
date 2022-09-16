package footprints.footprints.domain.member.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//DTO 명명법 -> Res~DTO | Req~DTO
@AllArgsConstructor
@Getter
@Setter
public class ResLoginedMemberDTO {
    private String nick;
    private String email;
    private String area;
}
