package footprints.footprints.domain.member.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class LikesMemberDTO {

    private List<Long> post_id;
}
