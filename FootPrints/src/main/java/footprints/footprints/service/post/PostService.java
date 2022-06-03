package footprints.footprints.service.post;

import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostDTO;

import java.util.List;

public interface PostService {

    void join(PostDTO postDTO);

    void update(PostDTO postDTO);

    List<Post> getPostList(String areaName); // 전체 리스트뷰

    Post getPost(Long post_num); // 하나의 상세페이지로 이동
}
