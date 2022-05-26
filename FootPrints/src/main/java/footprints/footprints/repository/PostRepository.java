package footprints.footprints.repository;

import footprints.footprints.domain.PostDTO;

public interface PostRepository {

    void save(PostDTO postDTO);
}
