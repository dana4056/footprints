package footprints.footprints.domain.post;


import com.fasterxml.jackson.annotation.JsonIgnore;
import footprints.footprints.domain.member.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "member")
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;         // 글 id num
    private String post_name;     // 글 제목
    private String post_content;  // 글 내용
    private String category;      // 음식 카테고리
    private String take_loc;      // 음식 나눌 장소(닉네임)
    private String post_area;     // 음식 나누는 주소!
    private int participant_num;  // 현재 참가 인원
    private int max_person_num;       // 모집 인원
    private String valid_time;       // 게시물 유효 시간
    @CreatedDate
    private LocalDateTime createdDate;  //게시물 등록 시간
    private int view_num;         // 조회수
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="MEMBER_NICK")
//    @JsonIgnore
    private Member member;
    private long x;
    private long y;

    @Builder
    public Post(String post_name, String post_content, String category, String take_loc, String post_area,
                int participant_num, int max_person_num, String valid_time, int view_num, Member member, long x, long y){
        this.post_name = post_name;
        this.post_content = post_content;
        this.category = category;
        this.take_loc = take_loc;
        this.post_area = post_area;
        this.participant_num = participant_num;
        this.max_person_num = max_person_num;
        this.valid_time = valid_time;
        this.view_num = view_num;
        this.member = member;
        this.x = x;
        this.y=  y;
    }

    public void Update(PostDTO postDTO){
        this.post_name = postDTO.getPost_name();
        this.post_content = postDTO.getPost_content();
        this.category = postDTO.getCategory();
        this.take_loc = postDTO.getTake_loc();
        this.post_area = postDTO.getPost_area();
        this.participant_num = postDTO.getParticipant_num();
        this.max_person_num = postDTO.getMax_person_num();
        this.valid_time = postDTO.getValid_time();
        this.view_num = postDTO.getView_num();
//        this.member = postDTO.getMember();
    }

    public void Plus_view(){
        this.view_num += 1;
    }
}

