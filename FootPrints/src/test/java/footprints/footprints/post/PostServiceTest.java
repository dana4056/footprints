package footprints.footprints.post;

import footprints.footprints.domain.member.MemberDTO;
import footprints.footprints.domain.post.PostDTO;
import footprints.footprints.repository.post.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
class PostServiceTest {

    @Autowired
    PostRepository postRepository;

    @Test
    void save() {

        PostDTO postDTO = new PostDTO("글제목", "글내용", "카테고리", "장소", 3, 3, 0);

        postRepository.save(postDTO);


    }


}