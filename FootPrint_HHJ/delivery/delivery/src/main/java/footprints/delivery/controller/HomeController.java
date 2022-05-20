package footprints.delivery.controller;

import footprints.delivery.domain.Member;
import footprints.delivery.service.CertifiedService;
import footprints.delivery.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor

public class HomeController {

    private final MemberService memberService;
    private final CertifiedService certifiedService;

    @PostMapping("/signup")
    public ResponseEntity<String> postMember(@RequestBody Member member) throws Exception {
        log.info("아이디 = {}", member.getEmail());
        log.info("비밀번호 = {}", member.getPw());
        log.info("닉네임 = {}", member.getNick());
        log.info("핸드폰 번호 = {}", member.getPhone());
        log.info("지역 = {}", member.getArea());
        memberService.join(member);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    //이메일 받아온 부분
    @PostMapping(value = "/signup/check-email")
    public ResponseEntity<String> checkEmail(@RequestBody String email){
        log.info("--------email:{}", email);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    //닉네임 받아온 부분
    @PostMapping(value = "/signup/check-nick")
    public ResponseEntity<String> checkNick(@RequestBody String nick){
        log.info("--------nick:{}", nick);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    //전화번호 받아온 부분
    @PostMapping(value = "/signup/authentic-code")
    public void sendMessage(@RequestBody String Phone) throws CoolsmsException {
        log.info("--------nick:{}", Phone);
        certifiedService.sendmessage(Phone);
    }

//    public void smsVerification(@RequestBody String number){
//        certifiedService.verifySms(number);
//    }
}
