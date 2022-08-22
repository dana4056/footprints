package footprints.footprints.domain.roomInfo;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

public class RoomInfoDTO {
    private Member member;

    private Post post;

    public RoomInfoDTO(RoomInfo roomInfo){
        this.member = roomInfo.getMember();
        this.post = roomInfo.getPost();
    }
}
