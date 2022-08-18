package footprints.footprints.service.post;

import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostDTO;

import java.util.List;

public interface PostService {

    void join(PostDTO postDTO);

    void update(PostDTO postDTO);

    List<Post> getPostList(String area_name); // 전체 리스트뷰

    Post getPost(Long post_num); // 하나의 상세페이지로 이동

    List<Post> getCategoryList(String category, String areaName); // 카테고리별 리스트뷰

    List<Post> getSortTimeList(String time, String areaName); //시간별 리스트뷰

    public void plusView(Post post);
}
