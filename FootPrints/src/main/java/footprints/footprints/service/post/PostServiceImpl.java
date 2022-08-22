package footprints.footprints.service.post;

import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostDTO;
import footprints.footprints.repository.post.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    @Override
    public void join(PostDTO postDTO){
        postRepository.save_d(postDTO);
    }

    @Override
    public void update(PostDTO postDTO){
        //프론트에서 넘겨받은 id를 이용하여 해당 post를 db에서 검색
        Post post = postRepository.findDetail(postDTO.getPost_id());
        post.Update(postDTO);
        //PostDTO postDTO1 = new PostDTO(post);
        postRepository.save(post);
    }

    @Override
    public void remove(Long post_id){
        Post post = postRepository.findDetail(post_id);
        postRepository.delete(post);
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

    @Override
    public List<Post> getSortingList(String category, String sort_criteria, String area) {
        List<Post> posts = postRepository.findSortAll(category, sort_criteria, area);
        return posts;
    }


    @Override
    public void plusView(Post post) {
        post.Plus_view();
        postRepository.save(post);
    }

    public void delete(Long post_id) {
        postRepository.delete_all(post_id);
    }
}
