package footprints.footprints.controller.login;

import footprints.footprints.domain.member.MemberDTO;
import footprints.footprints.domain.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/login")
    public ResponseEntity<String> checkLogin(@RequestBody MemberDTO memberDTO, HttpServletResponse response){
        int checkLogin = loginService.loginCheck(memberDTO);
        if(checkLogin == 1){  //로그인 성공
            log.info("로그인 성공");

            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        }
        else if(checkLogin == 0){ // 해당 닉네임 없음(없는 계정)
            log.info("로그인 실패: 해당 닉네임 존재하지 않음");
            return new ResponseEntity<String>("LOGIN_FAILED:NO_ID", HttpStatus.BAD_REQUEST);
        }
        else{  // 비밀번호 불일치
            log.info("로그인 실패: 비밀번호가 일치하지 않음");
            return new ResponseEntity<String>("LOGIN_FAILED:NOT_MATCH_PW", HttpStatus.NOT_FOUND);
        }
    }

}
