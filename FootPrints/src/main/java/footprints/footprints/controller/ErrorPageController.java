package footprints.footprints.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class  ErrorPageController implements ErrorController {
    // 새로고침 시 index.html으로
    @GetMapping("/error")
    public String redirectRoot(){
        log.info("에러페이지");
        return "index";
    }

    public String getErrorPath(){
        return null;
    }
}
