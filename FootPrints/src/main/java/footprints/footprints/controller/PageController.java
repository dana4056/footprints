package footprints.footprints.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class PageController implements ErrorController {

        private final String ERROR_PATH = "/error";

        @GetMapping(ERROR_PATH)
        public String errorController(){
            log.info("에러페이지->리다이렉트");
            return "index.html";
        }
}
