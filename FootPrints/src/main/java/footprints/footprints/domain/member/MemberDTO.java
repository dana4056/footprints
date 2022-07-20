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


    public MemberDTO(Member member){
        this.nick = member.getNick();
        this.email = member.getEmail();
        this.pw = member.getPw();
        this.area = member.getArea();
    }

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
