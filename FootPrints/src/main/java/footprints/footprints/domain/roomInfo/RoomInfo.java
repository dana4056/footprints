package footprints.footprints.domain.roomInfo;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RoomInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long room_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="NICK")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="POST_ID")
    private Post post;

    @Builder
    public RoomInfo(Member member, Post post){
        this.member = member;
        this.post = post;
    }

    public void Update(RoomInfoDTO roomInfoDTO){
        this.member = roomInfoDTO.getMember();
        this.post = roomInfoDTO.getPost();
    }
}
