package footprints.footprints.domain.room_Info;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.post.Post;
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
public class room_Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long room_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="POST_ID")
    private Post post;


}
