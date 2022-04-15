package footprints.join_example.joinservice.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private String memberId;
    private String memberPw;
    private String nickName;
    private String phoneNumber;

    public Member(String memberId, String memberPw, String nickName, String phoneNumber) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
    }
}
