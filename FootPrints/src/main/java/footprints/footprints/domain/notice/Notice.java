package footprints.footprints.domain.notice;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.post.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   
    private String title;
    private String author; // 작성자는 role을 부여해서 작성자만 보이는 + 버튼 만들고 작성자만이 create 할 수 있도록
    private String post_time;
    private String content;
    private int view_num;

    @Builder
    public Notice(String title, String author, String post_time, String content, int view_num){
        this.title = title;
        this.author = author;
        this.post_time = post_time;
        this.content = content;
        this.view_num = view_num;
    }

    public void Update(NoticeDTO_cID noticeDTO_cID){
        this.title = noticeDTO_cID.getTitle();
        this.author = noticeDTO_cID.getAuthor();
        this.post_time = noticeDTO_cID.getPost_time();
        this.content = noticeDTO_cID.getContent();
        this.view_num = noticeDTO_cID.getView_num();
    }

    public void Plus_view(){
        this.view_num += 1;
    }
}

