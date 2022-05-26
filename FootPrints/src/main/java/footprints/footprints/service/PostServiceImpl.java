package footprints.footprints.service;

import footprints.footprints.domain.PostDTO;
import footprints.footprints.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostServiceImpl implements  PostService{

    private final PostRepository postRepository;

    @Override
    public void join(PostDTO postDTO){
        postRepository.save(postDTO);
    }
}
