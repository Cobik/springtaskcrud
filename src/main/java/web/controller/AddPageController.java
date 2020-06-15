package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserServiceImpl;

import java.sql.SQLException;

@Controller
@RequestMapping("/addUser")
public class AddPageController {

    @RequestMapping(method = RequestMethod.GET)
    public String printAddUser(){
        return "adduser";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addUser(@RequestParam("name") String name
            , @RequestParam("username") String username
            , @RequestParam("password") String password
            , @RequestParam("role") String role
    ) {
        User user = new User(name,username,password,role);
        try {
            UserServiceImpl.getInstance().addUser(user);
            return "redirect:/main";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
