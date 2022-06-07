package footprints.footprints.domain.post;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)

public class Post {

    @Id
    @GeneratedValue
    private Long post_id;         // 글 id num
    private String post_name;     // 글 제목
    private String post_content;  // 글 내용
    private String category;      // 음식 카테고리
    private String area_name;     // 행정지역명
    private String take_loc;      // 음식 나눌 장소
    private int participant_num;  // 현재 참가 인원
    private int person_num;       // 모집 인원
    private int valid_time;       // 게시물 유효 시간
    @CreatedDate
    private LocalDateTime createdDate;  //게시물 등록 시간


    @Builder
    public Post(String post_name, String post_content, String category, String area_name, String take_loc,
                int participant_num, int person_num, int valid_time){
        this.post_name = post_name;
        this.post_content = post_content;
        this.category = category;
        this.area_name = area_name;
        this.take_loc = take_loc;
        this.participant_num = participant_num;
        this.person_num = person_num;
        this.valid_time = valid_time;
    }
}

