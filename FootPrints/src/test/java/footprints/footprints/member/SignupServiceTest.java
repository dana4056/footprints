//package footprints.footprints.member;
//
//import footprints.footprints.domain.member.Member;
//import footprints.footprints.domain.member.MemberDTO;
//import footprints.footprints.domain.post.Post;
//import footprints.footprints.domain.post.PostDTO;
//import footprints.footprints.repository.member.MemberRepository;
//import footprints.footprints.repository.member.MemberRepositoryImpl;
//import footprints.footprints.repository.post.PostRepository;
//import footprints.footprints.service.post.PostService;
//import footprints.footprints.service.signup.SignupService;
//import footprints.footprints.service.signup.SignupServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//
//import javax.transaction.Transactional;
//
//
//@SpringBootTest
//@Transactional
//@Rollback(false)
//public class SignupServiceTest {
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
//    @Autowired
//    SignupService signupService;
//
//    @Test
//    void save() {
//        Member member = new Member("Hyukjin", "gurwlszx@naver.com", "1234", "성북구 정릉동");
//
//        //MemberDTO memberDTO = new MemberDTO(member);
//
//        memberRepository.save1(member);
//
//        Post post = new Post("글제목", "글내용", "카테고리", "장소", 3, 3, "0", 100, member);
//
//        postRepository.save1(post);
//
//    }
//}
