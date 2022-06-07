package footprints.footprints.domain.post;


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

public class PostDTO {

    private String post_name;     // 글 제목
    private String post_content;  // 글 내용
    private String category;      // 음식 카테고리
    private String area_name;     // 행정지역명
    private String take_loc;      // 음식 나눌 장소
    private int participant_num;  // 현재 참가 인원
    private int person_num;       // 모집 인원
    private int valid_time;       // 게시물 유효 시간


    public Post toEntity() {
        return Post.builder()
                .post_name(post_name)
                .post_content(post_content)
                .category(category)
                .area_name(area_name)
                .take_loc(take_loc)
                .participant_num(participant_num)
                .person_num(person_num)
                .valid_time(valid_time)
                .build();
    }
}
