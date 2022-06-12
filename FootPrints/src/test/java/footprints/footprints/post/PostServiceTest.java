package footprints.footprints.post;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.MemberDTO;
import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostAreaDTO;
import footprints.footprints.domain.post.PostDTO;
import footprints.footprints.repository.member.MemberRepository;
import footprints.footprints.repository.post.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)

class PostServiceTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {

        Member member1 = new Member("Hyukjin", "gurwlszx@naver.com", "1234", "성북구 정릉동");
        Member member2 = new Member("Sunjin", "asdf@naver.com", "5678", "은평구 불광동");

//        MemberDTO memberDTO1 = new MemberDTO(member1);
//        MemberDTO memberDTO2 = new MemberDTO(member2);

        memberRepository.save1(member1);
        memberRepository.save1(member1);

        Post post1 = new Post("글제목", "글내용", "카테고리", "지역명", "장소", 3, 3, 0, member1);
        Post post2 = new Post("제목", "내용", "카테고리", "지역", "장소", 1, 5, 1, member2);

//        PostDTO postDTO1 = new PostDTO(post1);
//        PostDTO postDTO2 = new PostDTO(post2);

        postRepository.save1(post1);
        postRepository.save1(post2);
//        Member member1 = new Member("Hyukjin", "gurwlszx@naver.com", "1234", "성북구 정릉동");
//
//        MemberDTO memberDTO = new MemberDTO(member1);
//
//        memberRepository.save(memberDTO);
//
//        Post post1 = new Post("글제목", "글내용", "카테고리", "지역명", "장소", 3, 3, 0, member1);
//
//        PostDTO postDTO = new PostDTO(post1);
//
//        postRepository.save(postDTO);
//
//        PostAreaDTO postAreaDTO = new PostAreaDTO(post1);
//
//        System.out.println(postAreaDTO.getMember().getArea());
//
//        List<Post> posts = member1.getPosts();
//
//        for (Post post : posts){
//            System.out.println(post.toString());
//        }
    }


}