package footprints.footprints.controller;

import footprints.footprints.domain.Member;
import footprints.footprints.service.MemberServiceImpl;
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

    private final MemberServiceImpl memberService;

    //회원가입 정보
    @PostMapping(value = "/signup")
    public ResponseEntity<String> create(@RequestBody Member member){
        log.info("member 이름 {}", member.getNick());
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
    public ResponseEntity<String> sendCode(@RequestBody String phone){
        log.info("--------Phone:{}", phone);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> checkLogin(@RequestBody Member member){
        log.info("--------Id:{}", member.getEmail());
        log.info("--------Pwd:{}", member.getPw());
        boolean checkLogin = memberService.checkLogin(member);
        if(checkLogin){
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("ANOTHER", HttpStatus.OK);
        }
    }
}

