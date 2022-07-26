package footprints.footprints.domain.notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Notice {
    @Id
    @GeneratedValue
    private Long id;   
    private String title;
    private String author; // 작성자는 role을 부여해서 작성자만 보이는 + 버튼 만들고 작성자만이 create 할 수 있도록
    private String post_time;
    private String content;
    private Long view_num;

    public void Plus_view(){
        this.view_num += 1;
    }
}
