package footprints.footprints.service.member;

import footprints.footprints.domain.member.DTO.LikesMemberDTO;
import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.post.PostDTO;
import footprints.footprints.repository.member.MemberRepository;
import footprints.footprints.repository.post.PostRepository;
import footprints.footprints.service.post.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
class MemberServiceImplTest {

    @Autowired
    PostRepository postRepository;

    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    PostService postService;


    @Test
    void 좋아요게시물_업로드_확인() {

//        private String post_name;     // 글 제목
//        private String post_content;  // 글 내용
//        private String category;      // 음식 카테고리
//        private String take_loc;      // 음식 나눌 장소
//        private int participant_num;  // 현재 참가 인원
//        private int max_person_num;       // 모집 인원
//        private String valid_time;       // 게시물 유효 시간
//        private int view_num;         // 조회수
//        private String nick;
//        private Member member;  //0725추가
//        private String area_name;  //0812추가
//        private long x;
//        private long y;
//        private int likes;
        PostDTO postDTO = new PostDTO("테스트", "테스트내용", "한식", "서경대", "");

        new LikesMemberDTO()

    }
}