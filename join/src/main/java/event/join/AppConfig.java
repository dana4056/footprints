package event.join;

import event.join.member.MemberRepository;
import event.join.member.MemberService;
import event.join.member.MemberServiceImpl;
import event.join.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository(){
        System.out.println("call AppConfig.memoryMemberRepository");
        return new MemoryMemberRepository();
    }
}
