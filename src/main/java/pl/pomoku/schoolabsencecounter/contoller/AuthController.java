package pl.pomoku.schoolabsencecounter.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/index")
    public String home(){
        return "index";
    }
}
