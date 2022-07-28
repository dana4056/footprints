package footprints.footprints.controller.member;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.MemberDTO;
import footprints.footprints.domain.member.MemberResponseDTO;
import footprints.footprints.jwt.JwtTokenProvider;
import footprints.footprints.repository.member.MemberRepository;
import footprints.footprints.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    private final MemberRepository memberRepository;

    private final JwtTokenProvider jwtTokenProvider;

//    @GetMapping(value = "/user/1")
//    public ResponseEntity<String> auth(){
//        return new ResponseEntity<String>("SUCCESS AUTH?", HttpStatus.OK);
//    }

    @GetMapping(value = "/user")
    public ResponseEntity<MemberResponseDTO> fetchMember(Authentication authentication){
        Member member = (Member) authentication.getPrincipal();
        log.info("멤버 정보 : {}, {}, {}, {}", member.getArea(), member.getRoles(), member.getNick(), member.getEmail());
        MemberResponseDTO responseMember = new MemberResponseDTO(member.getNick(), member.getArea());
//        responseMember.setArea(member.getArea());
        return new ResponseEntity<MemberResponseDTO>(responseMember, HttpStatus.OK);
    }

    // 회원가입
    @PostMapping(value = "/signup")
    public ResponseEntity<String> create(@RequestBody Member member){
        log.info("멤버 정보 : {}", member.getPw());
        memberService.join(member);
        boolean signupImpossible = memberService.emailOverlapCheck(member.getEmail());

        if(signupImpossible){
            log.info("회원가입 실패: nick = {}", member.getNick());
            return new ResponseEntity<String>("FAILED", HttpStatus.CONFLICT);
        }
        else{
            log.info("회원가입 성공: nick = {}", member.getNick());
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        }

    }

    // 이메일 중복확인
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

    // 닉네임 중복확인
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

    // 로그인
    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody MemberDTO memberDTO){
        int checkLogin = memberService.loginCheck(memberDTO);
        if(checkLogin == 1){  //로그인 성공
            log.info("로그인 성공");

            Member loginMember = memberRepository.findByNick(memberDTO.getNick());
            String token = jwtTokenProvider.createToken(loginMember);
            return new ResponseEntity<String>(token, HttpStatus.OK);
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
//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//        log.info("로그아웃 성공(세션 종료)");
//        return new ResponseEntity<String>("SUCCESS LOGOUT", HttpStatus.OK);
//    }
    
    // 아이디 찾기
    @PostMapping(value = "/findID")
    public ResponseEntity<String> findID(@RequestBody String email){
        log.info("--------[/findID] Email:{}", email);

        String Nick = memberService.findID(email);
        if(Nick == null){
            return new ResponseEntity<String>("CANNOT_FIND_ID", HttpStatus.OK);
            // 이후 프론트에서 찾을 수 없는 아이디입니다 표시
        }
        else{
            return new ResponseEntity<String>(Nick, HttpStatus.OK);
        }
    }

    // 비밀번호 찾기
    @PostMapping(value = "/findPW")
    public ResponseEntity<String> findPW(@RequestBody String email){
        log.info("--------[/findPW] Email:{}", email);
        String f_email = memberService.findPwd(email);
        if(f_email == null){
            return new ResponseEntity<String>("CANNOT_FIND_ID", HttpStatus.OK);
            // 이후 프론트에서 찾을 수 없는 아이디입니다 표시
        }
        else{
            return new ResponseEntity<String>(f_email, HttpStatus.OK);
        }
    }

    // 비밀번호 변경
    @PostMapping(value = "/ChangePW")  // 비밀번호를 바꾸는 로직은 넘겨줄때 member 객체 + String new_pwd 개념
    public ResponseEntity<String> ChangePW(@RequestBody MemberDTO memberDTO){
        //log.info("--------[/ChangePW] email:{}", memberDTO.getEmail());
        //log.info("--------new_Pwd:{}", memberDTO.getPw());
        memberService.changeDBPwd(memberDTO);
//        if(change) {
//            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK); //비밀번호 변경 성공
//        }
//        else{
//            return new ResponseEntity<String>("FAILED", HttpStatus.OK);
//        }
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK); //비밀번호 변경 성공
    }
}
