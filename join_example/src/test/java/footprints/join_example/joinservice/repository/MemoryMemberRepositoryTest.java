package footprints.join_example.joinservice.repository;

import footprints.join_example.joinservice.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void findById() {
        //given
        Member member1 = new Member("jung123@naver.com", "dbtjr123", "JARA", "01053874816");
        Member member2 = new Member("jung456@naver.com", "korea123", "SARA", "01012345678");
        repository.save(member1);
        repository.save(member2);

        //when
        Member result = repository.findById("jung123@naver.com").get();

        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void 회원ID중복확인() {
        //given
        Member member1 = new Member("jung123@naver.com", "dbtjr123", "JARA", "01053874816");
        Member member2 = new Member("jung456@naver.com", "korea123", "SARA", "01012345678");

        //when
        repository.save(member1);
        repository.save(member2);

        //then
        assertThat(member1.getMemberId()).isNotEqualTo(member2.getMemberId());
    }

    @Test
    public void 회원닉네임중복확인() {
        //given
        Member member1 = new Member("jung123@naver.com", "dbtjr123", "JARA", "01053874816");
        Member member2 = new Member("jung456@naver.com", "korea123", "SARA", "01012345678");

        //when
        repository.save(member1);
        repository.save(member2);

        //then
        assertThat(member1.getNickName()).isNotEqualTo(member2.getNickName());
    }

    @Test
    public void 비밀번호입력일치확인() {
        //jsp??
    }

}