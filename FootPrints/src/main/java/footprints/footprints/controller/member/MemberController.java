package footprints.footprints.controller.member;

import footprints.footprints.SessionConst;
import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.MemberChangeDTO;
import footprints.footprints.domain.member.MemberDTO;
import footprints.footprints.repository.member.MemberRepositoryImpl;
import footprints.footprints.service.change.ChangeService;
import footprints.footprints.service.login.LoginService;
import footprints.footprints.service.security.SecurityService;
import footprints.footprints.service.signup.SignupService;
import footprints.footprints.service.signup.SignupServiceImpl;
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
public class MemberController {
//    private final SignupServiceImpl signupService;
    private final SignupService signupService;
    private final LoginService loginService;
    private final MemberRepositoryImpl memberRepository;
    private final ChangeService changeService;
    private final SecurityService securityService;

    // 회원가입
    @PostMapping(value = "/signup")
    public ResponseEntity<String> create(@RequestBody MemberDTO memberDTO){
        log.info("회원가입 성공: nick = {}", memberDTO.getNick());
        signupService.join(memberDTO);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    // 이메일 중복확인
    @PostMapping(value = "/signup/check-email")
    public ResponseEntity<String> checkEmail(@RequestBody String email){
        log.info("이메일 중복체크 : {}", email);
        boolean duplication = signupService.emailOverlapCheck(email);

        if(duplication) {
            log.info("사용 가능한 이메일");
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        }
        else {
            log.info("이메일 중복");
            return new ResponseEntity<String>("FAILED", HttpStatus.CONFLICT);
        }
    }

    // 닉네임 중복확인
    @PostMapping(value = "/signup/check-nick")
    public ResponseEntity<String> checkNick(@RequestBody String nick){
        log.info("닉네임 중복체크 : {}", nick);
        boolean duplication = signupService.nickOverlapCheck(nick);
        if(duplication) {
            log.info("사용 가능한 닉넴");
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        }
        else {
            log.info("닉네임 중복");
            return new ResponseEntity<String>("FAILED", HttpStatus.CONFLICT);
        }
    }

    // 로그인
    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody MemberDTO memberDTO, HttpServletResponse response, HttpServletRequest request){
        int checkLogin = loginService.loginCheck(memberDTO);
        if(checkLogin == 1){  //로그인 성공
            log.info("로그인 성공");

            Member loginMember = memberRepository.findByNick(memberDTO.getNick());

            String token = securityService.makeJwtToken(loginMember);
            log.info("토큰: {}", token);


//            //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
//            HttpSession session = request.getSession();
//            //세션에 로그인 회원 정보 보관
//            session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
//
//            Cookie mySessionCookie = new Cookie("JSESSIONID", session.getId());
//            response.addCookie(mySessionCookie);

            return ResponseEntity.ok(token);
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

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        log.info("로그아웃 성공(세션 종료)");
        return new ResponseEntity<String>("SUCCESS LOGOUT", HttpStatus.OK);
    }
    @PostMapping(value = "/findID")
    public ResponseEntity<String> findID(@RequestBody String email){
        log.info("--------Email:{}", email);
        String ID = changeService.findID(email);
        if(ID == "null"){
            return new ResponseEntity<String>("CANNOT_FIND_ID", HttpStatus.OK);
            // 이후 프론트에서 찾을 수 없는 아이디입니다 표시
        }
        else{
            return new ResponseEntity<String>(ID, HttpStatus.OK);
        }
    }

    // 비밀번호 찾기
    @PostMapping(value = "/findPW")
    public ResponseEntity<String> findPW(@RequestBody String email){
        log.info("--------Email:{}", email);
        String ID = changeService.findPwd(email);
        if(ID == "null"){
            return new ResponseEntity<String>("CANNOT_FIND_ID", HttpStatus.OK);
            // 이후 프론트에서 찾을 수 없는 아이디입니다 표시
        }
        else{
            return new ResponseEntity<String>(ID, HttpStatus.OK);
        }
    }

    // 비밀번호 변경
    @PostMapping(value = "/ChangePW")  //비밀번호를 바꾸는 로직은 넘겨줄떄 member 객체 + String new_pwd 개념
    public ResponseEntity<String> ChangePW(@RequestBody MemberChangeDTO memberChangeDTO){
        log.info("--------email:{}", memberChangeDTO.getEmail());
        log.info("--------new_Pwd:{}", memberChangeDTO.getNew_pw());
        boolean change = changeService.changePwd(memberChangeDTO);
        if(change) {
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK); //비밀번호 변경 성공
        }
        else{
            return new ResponseEntity<String>("FAILED", HttpStatus.OK);
        }
    }
}
