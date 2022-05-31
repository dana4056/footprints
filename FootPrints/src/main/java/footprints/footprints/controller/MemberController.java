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
        log.info("--------Nick:{}", memberDTO.getNick());
        log.info("--------Pw:{}", memberDTO.getPw());
        int checkLogin = memberService.loginCheck(memberDTO);
        if(checkLogin == 1){  //로그인 성공
            log.info("로그인 성공");
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        }
        else if(checkLogin == 0){ // 해당 닉네임 없음(없는 계정)
            return new ResponseEntity<String>("LOGIN_FAILED:NO_ID", HttpStatus.BAD_REQUEST);
        }
        else{  // 비밀번호 불일치
            return new ResponseEntity<String>("LOGIN_FAILED:NOT_MATCH_PW", HttpStatus.NOT_FOUND);
        }
    }
}

