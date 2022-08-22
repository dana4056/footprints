package footprints.footprints.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class ErrorPageController implements ErrorController {

        private final String ERROR_PATH = "/error";

        @GetMapping(ERROR_PATH)
        public String redirectRoot(){
            return "index.html";
        }

        public String getErrorPath(){
            return null;
        }
}
