package footprints.footprints.service;

import footprints.footprints.domain.PostDTO;
import footprints.footprints.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
