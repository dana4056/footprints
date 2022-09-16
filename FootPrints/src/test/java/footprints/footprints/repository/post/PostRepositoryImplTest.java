package footprints.footprints.repository.post;

import footprints.footprints.domain.post.Post;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class PostRepositoryImplTest {

    @Test
    void 동위치에_맞게_생성() {
        new Post("1", "테스트", "한식", "편의점 앞", "영등포구 신길동", 3, 5, "2000-02-15", "" )
    }
}