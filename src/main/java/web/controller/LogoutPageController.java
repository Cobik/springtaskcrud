package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/logout")
public class LogoutPageController {

    @RequestMapping(method = RequestMethod.GET)
    public String LogOut(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
