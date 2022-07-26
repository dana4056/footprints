package footprints.footprints.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;

@AllArgsConstructor
@Getter @Setter
public class MemberDTO {
    private String nick;
    private String email;
    private String pw;
    private String area;

    public Member toEntity() {
        return Member.builder()
                .nick(nick)
                .email(email)
                .pw(pw)
                .area(area)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
    }

}
