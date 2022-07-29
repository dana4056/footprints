package footprints.footprints.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class  ErrorPageController{
    // 새로고침 시 index.html으로
    @GetMapping("/error")
    public String redirectRoot(){
        return "index";
    }
}
