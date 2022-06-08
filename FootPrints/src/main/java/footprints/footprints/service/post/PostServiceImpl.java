package footprints.footprints.service.post;

import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostDTO;
import footprints.footprints.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements  PostService{

    private final PostRepository postRepository;

    @Override
    public void join(PostDTO postDTO){
        postRepository.save(postDTO);
    }

    @Override
    public void update(PostDTO postDTO){ postRepository.save(postDTO); }

    @Override
    public List<Post> getPostList(String area_name) {
        List<Post> posts = postRepository.findAll(area_name);
        return posts;
    }

    @Override
    public Post getPost(Long post_num) {
        Post detail = postRepository.findDetail(post_num);
        return detail;
    }
}
