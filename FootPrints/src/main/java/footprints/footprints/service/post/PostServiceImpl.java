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
    public List<Post> getPostList(String areaName) {
        List<Post> posts = postRepository.findAll(areaName);
        List<Post> postList = new ArrayList<>();

        for(Post post : posts) {
            Post dto = Post.builder()
                    .post_name(post.getPost_name())
                    .post_content(post.getPost_content())
                    .category(post.getCategory())
                    .take_loc(post.getTake_loc())
                    .person_num(post.getPerson_num())
                    .valid_time(post.getValid_time())
                    .participant_num(post.getParticipant_num())
                    .build();

            postList.add(dto);
        }

        return postList;
    }

    @Override
    public Post getPost(Long post_num) {
        Post detail = postRepository.findDetail(post_num);
        return detail;
    }
}
