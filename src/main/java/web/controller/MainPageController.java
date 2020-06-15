package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.User;
import web.service.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainPageController {

    UserServiceImpl userService = UserServiceImpl.getInstance();

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String printAllUsers(ModelMap model) {

        List<User> users = userService.getAllUsers();
        model.addAttribute("usersFromServer",users);
        return "mainpage";
    }
}
