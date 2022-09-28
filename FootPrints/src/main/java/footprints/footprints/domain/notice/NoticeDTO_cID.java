package footprints.footprints.domain.notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NoticeDTO_cID {

    private Long id;
    private String title;
    private String author; // 작성자는 role을 부여해서 작성자만 보이는 + 버튼 만들고 작성자만이 create 할 수 있도록
    private String post_time;
    private String content;
    private int view_num;
}