package footprints.footprints.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
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


    public Member toEntity() {
        return Member.builder()
                .email(email)
                .pw(pw)
                .nick(nick)
                .phone(phone)
                .area(area)
                .build();
    }
}
