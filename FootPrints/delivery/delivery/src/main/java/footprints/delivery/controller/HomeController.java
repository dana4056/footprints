package footprints.delivery.controller;

import footprints.delivery.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@Controller
public class HomeController {



    @PostMapping("/signup")
    public ResponseEntity<String> postMember(@RequestBody Member member) throws Exception {
        log.info("아이디 = {}", member.getEmail());
        log.info("비밀번호 = {}", member.getPw());
        log.info("닉네임 = {}", member.getNick());
        log.info("핸드폰 번호 = {}", member.getPhone());
        log.info("지역 = {}", member.getArea());
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
}
