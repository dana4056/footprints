package footprints.footprints.controller.member;

import footprints.footprints.domain.member.DTO.ReqChangePwDTO;
import footprints.footprints.domain.member.DTO.ReqLoginMemberDTO;
import footprints.footprints.domain.member.DTO.ResLoginedMemberDTO;
import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.DTO.MemberDTO;
import footprints.footprints.security.jwt.JwtTokenProvider;
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

    //토큰에서 멤버 정보(nick, area) 추출해서 반환
    @GetMapping(value = "/user")
    public ResponseEntity<ResLoginedMemberDTO> fetchMember(Authentication authentication){
        Member principal = (Member)authentication.getPrincipal();
        ResLoginedMemberDTO loginedMember = new ResLoginedMemberDTO(principal.getNick(), principal.getArea());
        log.info("-----[MemberController fetchMember] return {}",loginedMember.getNick());
        return new ResponseEntity<ResLoginedMemberDTO>(loginedMember, HttpStatus.OK);
    }

    // 회원가입
    @PostMapping(value = "/signup")
    public ResponseEntity<String> create(@RequestBody MemberDTO memberDTO){
        memberService.join(memberDTO);
        boolean signupImpossible = memberService.emailOverlapCheck(memberDTO.getEmail());

        if(signupImpossible){
            log.info("-----[MemberController create] 회원가입 실패: nick = {}", memberDTO.getNick());
            return new ResponseEntity<String>("FAILED", HttpStatus.CONFLICT);
        }
        else{
            log.info("-----[MemberController create] 회원가입 성공: nick = {}", memberDTO.getNick());
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        }
    }

    // 이메일 중복확인
    @PostMapping(value = "/signup/check-email")
    public ResponseEntity<String> checkEmail(@RequestBody String email){
        log.info("-----[MemberController checkEmail] 이메일 중복체크 : {}", email);
        boolean duplication = memberService.emailOverlapCheck(email);

        if(duplication) {
            log.info("-----[MemberController checkEmail] 사용 가능한 이메일");
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        }
        else {
            log.info("-----[MemberController checkEmail] 이메일 중복");
            return new ResponseEntity<String>("FAILED", HttpStatus.CONFLICT);
        }
    }

    // 닉네임 중복확인
    @PostMapping(value = "/signup/check-nick")
    public ResponseEntity<String> checkNick(@RequestBody String nick){
        log.info("-----[MemberController checkNick] 닉네임 중복체크 : {}", nick);
        boolean duplication = memberService.nickOverlapCheck(nick);
        if(duplication) {
            log.info("-----[MemberController checkNick] 사용 가능한 닉넴");
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        }
        else {
            log.info("-----[MemberController checkNick] 닉네임 중복");
            return new ResponseEntity<String>("FAILED", HttpStatus.CONFLICT);
        }
    }

    // 로그인
    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody ReqLoginMemberDTO loginMemberDTO){
        int checkLogin = memberService.loginCheck(loginMemberDTO);
        if(checkLogin == 1){  //로그인 성공
            log.info("-----[MemberController login] 로그인 성공");

            Member loginMember = memberRepository.findByNick(loginMemberDTO.getNick());
            String token = jwtTokenProvider.createToken(loginMember);
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }
        else if(checkLogin == 0){ // 해당 닉네임 없음(없는 계정)
            log.info("-----[MemberController login] 로그인 실패: 해당 닉네임 존재하지 않음");
            return new ResponseEntity<String>("LOGIN_FAILED:NO_ID", HttpStatus.BAD_REQUEST);
        }
        else{  // 비밀번호 불일치
            log.info("-----[MemberController login] 로그인 실패: 비밀번호가 일치하지 않음");
            return new ResponseEntity<String>("LOGIN_FAILED:NOT_MATCH_PW", HttpStatus.NOT_FOUND);
        }
    }
    // 지역 찾기
    @PostMapping(value="/findUserArea")
    public ResponseEntity<String> findArea(@RequestBody String nick){
        String userArea = memberService.findArea(nick);
        return new ResponseEntity<String>(userArea, HttpStatus.OK);
    }

    // 아이디 찾기
    @PostMapping(value = "/findID")
    public ResponseEntity<String> findID(@RequestBody String email){
        log.info("-----[MemberController findID] 찾을 Email:{}", email);

        String Nick = memberService.findID(email);
        if(Nick == null){
            return new ResponseEntity<String>("CANNOT_FIND_ID", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>(Nick, HttpStatus.OK);
        }
    }

    // 비밀번호 찾기(비번 변경 전 이메일로 사용자 확인)
    @PostMapping(value = "/findPW")
    public ResponseEntity<String> findPW(@RequestBody String email){
        log.info("-----[MemberController findPW] Email:{}", email);
        String f_email = memberService.findPwd(email);
        if(f_email == null){
            return new ResponseEntity<String>("CANNOT_FIND_ID", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>(f_email, HttpStatus.OK);
        }
    }

    // 비밀번호 변경
    @PostMapping(value = "/ChangePW")  // 비밀번호를 바꾸는 로직은 넘겨줄때 member 객체 + String new_pwd 개념
    public ResponseEntity<String> ChangePW(@RequestBody ReqChangePwDTO changePwDTO){
        log.info("-----[MemberController ChangePW] email:{}", changePwDTO.getEmail());
        memberService.changeDBPwd(changePwDTO);
        log.info("-----[MemberController ChangePW] pw 변경 완료 -> {}", changePwDTO.getPw());
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK); //비밀번호 변경 성공
    }
}
