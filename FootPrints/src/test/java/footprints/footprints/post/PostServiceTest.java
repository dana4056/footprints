//package footprints.footprints.post;
//
//import footprints.footprints.domain.member.Member;
//import footprints.footprints.domain.post.Post;
//import footprints.footprints.domain.post.PostDTO;
//import footprints.footprints.repository.member.MemberRepository;
//import footprints.footprints.repository.post.PostRepository;
//import footprints.footprints.service.post.PostService;
//import org.junit.jupiter.api.Test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//
//import javax.transaction.Transactional;
//
//@SpringBootTest
//@Transactional
//@Rollback(false)
//
//class PostServiceTest {
//
//    @Autowired
//    PostRepository postRepository;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    PostService postService;
//
//    @Test
//    void save() {
//
//
//        Member member1 = new Member("Hyukjin", "gurwlszx@naver.com", "1234", "성북구 정릉동");
//
//        memberRepository.save1(member1);
//
//        Post post = new Post("글제목", "글내용", "카테고리", "장소", 3, 3, 0, 100, member1);
////        Post post2 = new Post("제목", "내용", "카테고리", "지역", "장소", 1, 5, 1, 50, member2);
//
//        //PostDTO postDTO1 = new PostDTO(post);
//
//        //postService.join(postDTO1);
//        postRepository.save1(post);
//
//        PostDTO postDTO2 = new PostDTO("변경된 글제목", "변경된 글내용", "변경된 카테고리", "변경된 장소", 3, 3, 0, 100);
//
//        System.out.println(post.getPost_id());
//        postService.update(post.getPost_id(), postDTO2);
//
//        post.Plus_view();
//
//        System.out.println(post.getView_num());
//
//        postRepository.save1(post);
////        postRepository.save1(post2);
////
////        member1.addPost(post1);
////        member1.addPost(post2);
////
////        PostAreaDTO postAreaDTO = new PostAreaDTO(post1);
////
////        System.out.println(postAreaDTO.getMember().getArea());
////
////        List<Post> posts = member1.getPosts();
////
////        for (Post post : posts){
////            System.out.println(post.toString());
////        }
//    }
//
//
//}