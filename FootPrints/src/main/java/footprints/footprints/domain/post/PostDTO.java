package footprints.footprints.domain.post;


import footprints.footprints.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

public class PostDTO {
    private long post_id;
    private String post_name;     // 글 제목
    private String post_content;  // 글 내용
    private String category;      // 음식 카테고리
    private String take_loc;      // 음식 나눌 장소
    private String post_area;     // 음식 나누는 주소!
    private int participant_num;  // 현재 참가 인원
    private int max_person_num;       // 모집 인원
    private String valid_time;       // 게시물 유효 시간
    private int view_num;         // 조회수
    private String nick;
    private double lat;
    private double lon;


    private Member member;  //0725추가

    public PostDTO(Post post){
        this.post_name = post.getPost_name();
        this.post_content = post.getPost_content();
        this.category = post.getCategory();
        this.take_loc = post.getTake_loc();
        this.post_area = post.getPost_area();
        this.participant_num = post.getParticipant_num();
        this.max_person_num = post.getMax_person_num();
        this.valid_time = post.getValid_time();
        this.view_num = post.getView_num();
        this.member = post.getMember();  //0725추가
        this.lat = post.getLat();
        this.lon = post.getLon();
    }


    public Post toEntity() {
        return Post.builder()
                .post_name(post_name)
                .post_content(post_content)
                .category(category)
                .take_loc(take_loc)
                .post_area(post_area)
                .participant_num(participant_num)
                .max_person_num(max_person_num)
                .valid_time(valid_time)
                .view_num(view_num)
                .build();
    }
}
