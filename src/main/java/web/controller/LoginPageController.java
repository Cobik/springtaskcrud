package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginPageController {
    @GetMapping
    public String loginUser() {
        return "login";
    }
}
