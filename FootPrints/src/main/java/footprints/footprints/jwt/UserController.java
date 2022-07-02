//package footprints.footprints.jwt;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Collections;
//import java.util.Map;
//
//@Slf4j
//@RequiredArgsConstructor
//@RestController
//public class UserController {
//
//    private final PasswordEncoder passwordEncoder;
//    private final JwtTokenProvider jwtTokenProvider;
//    private final UserRepository userRepository;
//
//    // 회원가입
//    @PostMapping("/join1")
//    public Long join(@RequestBody Map<String, String> user) {
//        log.info("------------------{}", user);
//        return userRepository.save(User.builder()
//            .email(user.get("email"))
//            .password(user.get("password"))
//            .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
//            .build()).getId();
//    }
//
//    // 로그인
//    @PostMapping("/login1")
//    public String login(@RequestBody Map<String, String> user) {
//        User member = userRepository.findByEmail(user.get("email"))
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
//        if (!user.get("password").equals(member.getPassword())) {
//            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
//        }
//        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
//    }
//}