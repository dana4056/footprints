package footprints.footprints.repository.post;

import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostDTO;

import java.util.List;

public interface PostRepository {

    void save(PostDTO postDTO);

    void save1(Post post);

    List<Post> findAll(String area_name);

    Post findDetail(Long post_num);

}
