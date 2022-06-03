package footprints.footprints.service;

import footprints.footprints.domain.Post;
import footprints.footprints.domain.PostDTO;

public interface PostService {

    void join(PostDTO postDTO);

    void update(PostDTO postDTO);
}
