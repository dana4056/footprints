package footprints.footprints.controller.login;

import footprints.footprints.SessionConst;
import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.MemberDTO;
import footprints.footprints.repository.member.MemberRepositoryImpl;
import footprints.footprints.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;
    private final MemberRepositoryImpl memberRepository;

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody MemberDTO memberDTO, HttpServletResponse response, HttpServletRequest request){
        int checkLogin = loginService.loginCheck(memberDTO);
        if(checkLogin == 1){  //로그인 성공
            log.info("로그인 성공");

            Member loginMember = memberRepository.findByNick(memberDTO.getNick());

            //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
            HttpSession session = request.getSession();
            //세션에 로그인 회원 정보 보관
            session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

            Cookie mySessionCookie = new Cookie("JSESSIONID", session.getId());
            response.addCookie(mySessionCookie);

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


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        log.info("로그아웃 성공(세션 종료)");
        return new ResponseEntity<String>("SUCCESS LOGOUT", HttpStatus.OK);
    }


}
