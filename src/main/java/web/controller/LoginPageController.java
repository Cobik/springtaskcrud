package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserServiceImpl;

import java.sql.SQLException;

@Controller
@RequestMapping("/login")
public class LoginPageController {

    UserServiceImpl userService = UserServiceImpl.getInstance();

    @RequestMapping(method = RequestMethod.GET)
    public String printLoginPage() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String LogIn(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            if (userService.validateUser(new User(username,password))) {
                return "redirect:/main";
            } else {
                return "redirect:/login";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
