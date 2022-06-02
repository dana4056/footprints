package footprints.footprints.controller.signup;

import footprints.footprints.domain.member.MemberDTO;
import footprints.footprints.domain.service.signup.SignupServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SignupController {

    private final SignupServiceImpl memberService;

    //회원가입 정보
    @PostMapping(value = "/signup")
    public ResponseEntity<String> create(@RequestBody MemberDTO memberDTO){
        log.info("회원가입 성공: nick = {}", memberDTO.getNick());
        memberService.join(memberDTO);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    //이메일 받아온 부분
    @PostMapping(value = "/signup/check-email")
    public ResponseEntity<String> checkEmail(@RequestBody String email){
        log.info("이메일 중복체크 : {}", email);
        boolean duplication = memberService.emailOverlapCheck(email);

        if(duplication) {
            log.info("사용 가능한 이메일");
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        }
        else {
            log.info("이메일 중복");
            return new ResponseEntity<String>("FAILED", HttpStatus.CONFLICT);
        }
    }

    //닉네임 받아온 부분
    @PostMapping(value = "/signup/check-nick")
    public ResponseEntity<String> checkNick(@RequestBody String nick){
        log.info("닉네임 중복체크 : {}", nick);
        boolean duplication = memberService.nickOverlapCheck(nick);
        if(duplication) {
            log.info("사용 가능한 닉넴");
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        }
        else {
            log.info("닉네임 중복");
            return new ResponseEntity<String>("FAILED", HttpStatus.CONFLICT);
        }
    }

}

