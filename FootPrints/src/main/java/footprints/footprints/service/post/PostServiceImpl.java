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
    public void update(Long post_id, PostDTO postDTO){
        //프론트에서 넘겨받은 id를 이용하여 해당 post를 db에서 검색
        Post post = postRepository.findDetail(post_id);
        post.Update(postDTO);
        //PostDTO postDTO1 = new PostDTO(post);
        postRepository.save1(post);
    }

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
