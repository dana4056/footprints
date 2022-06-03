package footprints.footprints.controller.change;


import footprints.footprints.domain.member.MemberChangeDTO;
import footprints.footprints.service.change.ChangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChangeController {

    private final ChangeService changeService;

    @PostMapping(value = "/findID")
    public ResponseEntity<String> findID(@RequestBody String email){
        log.info("--------Email:{}", email);
//        String refine_email = email.substring(0, 10) + "@" + email.substring(13,25); //데이터를 이상하게 받아와서 임의로 정제
        String ID = changeService.findID(email);
        if(ID == "null"){
            return new ResponseEntity<String>("CANNOT_FIND_ID", HttpStatus.OK);
            // 이후 프론트에서 찾을 수 없는 아이디입니다 표시
        }
        else{
            return new ResponseEntity<String>(ID, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/findPW")
    public ResponseEntity<String> findPW(@RequestBody String email){
        log.info("--------Email:{}", email);
//        String refine_email = email.substring(0, 10) + "@" + email.substring(13,25); //데이터를 이상하게 받아와서 일단 임의로 정제 헤더에 plain/context? 넣으면 해결 가능 근데 지금 다른데 집중중이라 안됨 불가능
        String ID = changeService.findPwd(email);
        if(ID == "null"){
            return new ResponseEntity<String>("CANNOT_FIND_ID", HttpStatus.OK);
            // 이후 프론트에서 찾을 수 없는 아이디입니다 표시
        }
        else{
            return new ResponseEntity<String>(ID, HttpStatus.OK);
        }
    }

    // 비밀번호를 찾아주는 개념이 아니니까 비밀번호를 교체하는 개념이니까 필요 X
//    @PostMapping(value = "/findPW")
//    public ResponseEntity<String> findPW(@RequestBody String email){
//        log.info("--------Email:{}", email);
//        String Pwd = memberService.findPwd(email);
//        if(Pwd == "null"){
//            return new ResponseEntity<String>("CANNOT_FIND_ID", HttpStatus.OK);
//            // 이후 프론트에서 찾을 수 없는 비밀번호입니다 표시
//        }
//        else{
//            return new ResponseEntity<String>(Pwd, HttpStatus.OK);
//        }
//    }

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
