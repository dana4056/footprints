package footprints.footprints.domain.post;


import footprints.footprints.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)

public class PostAreaDTO {

    private Long post_id;       //글 id num
    private Member member;


    public PostAreaDTO(Post post){
        this.post_id = post.getPost_id();
        this.member = post.getMember();
    }
}
