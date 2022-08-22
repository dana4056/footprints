package footprints.footprints.repository.post;

import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostDTO;

import java.util.List;

public interface PostRepository {

    void save_d(PostDTO postDTO);

    void save(Post post);

    void delete(Post post);

    List<Post> findAll(String area_name);

    Post findDetail(Long post_num);

    List<Post> findSortAll(String category, String sort_criteria, String area);

    Post findByPostId(Long post_id);

}
