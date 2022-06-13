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
    private String take_loc;      // 음식 나눌 장소
    private int participant_num;  // 현재 참가 인원
    private int max_person_num;       // 모집 인원
    private String valid_time;       // 게시물 유효 시간
    private int view_num;         // 조회수
    // ------- member entity 참조할건데 임시로 --------------
    private String user_name;     // 작성자 이름
    private String area_name;     // 행정지역명

    public Post toEntity() {
        return Post.builder()
                .post_name(post_name)
                .post_content(post_content)
                .category(category)
                .take_loc(take_loc)
                .participant_num(participant_num)
                .max_person_num(max_person_num)
                .valid_time(valid_time)
                .view_num(view_num)
                .user_name(user_name)
                .area_name(area_name)
                .build();
    }
}
