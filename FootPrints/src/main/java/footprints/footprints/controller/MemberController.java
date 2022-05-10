package footprints.footprints.controller;

import footprints.footprints.domain.Member;
import footprints.footprints.domain.MemberForm;
import footprints.footprints.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    //@GetMapping(value = "/signup")
//    @GetMapping(value = "/signup")
//    public String signup(){
//        return "signUp";
//    }

//    @PostMapping(value = "/signup")
//    public String create(MemberForm memberform){
//        Member member = new Member();
//        member.setNick(memberform.getName());
//        log.info("member 이름 {}", member.getNick());
//        memberService.join(member);
//        return "redirect:/";
//    }

    @PostMapping(value = "/signup")
    public ResponseEntity<String> create(@RequestBody Member member){
        log.info("member 이름 {}", member.getNick());
        memberService.join(member);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/signup-completed")
    public String sendNick(@RequestBody Member member){


        return "hi";
    }
}

