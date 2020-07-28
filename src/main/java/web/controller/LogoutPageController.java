package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/logout")
public class LogoutPageController {

    @GetMapping
    public String LogOut(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
