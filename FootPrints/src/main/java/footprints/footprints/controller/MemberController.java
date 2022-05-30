package footprints.footprints.controller;

import footprints.footprints.domain.Member;
import footprints.footprints.domain.MemberDTO;
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
    public ResponseEntity<String> create(@RequestBody MemberDTO memberDTO){
        log.info("member 이름 {}", memberDTO.getNick());
        memberService.join(memberDTO);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    //이메일 받아온 부분
    @PostMapping(value = "/signup/check-email")
    public ResponseEntity<String> checkEmail(@RequestBody String email){
        log.info("--------email:{}", email);
        boolean duplication = memberService.emailOverlapCheck(email);

        if(duplication) {
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
            // 아이디 생성 가능
        }
        else {
            return new ResponseEntity<String>("FAILED", HttpStatus.CONFLICT);
            // 아이디 생성 불가
        }
    }

    //닉네임 받아온 부분
    @PostMapping(value = "/signup/check-nick")
    public ResponseEntity<String> checkNick(@RequestBody String nick){
        log.info("--------nick:{}", nick);
        boolean duplication = memberService.nickOverlapCheck(nick);
        if(duplication) {
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("FAILED", HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> checkLogin(@RequestBody MemberDTO memberDTO){
        log.info("--------Id:{}", memberDTO.getEmail());
        log.info("--------Pwd:{}", memberDTO.getPw());
        boolean checkLogin = memberService.loginCheck(memberDTO);
        if(checkLogin){
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("LOGIN_FAILED", HttpStatus.OK);
        }
    }
}

