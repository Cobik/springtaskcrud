package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserServiceImpl;

import java.sql.SQLException;

@Controller
@RequestMapping("/edit")
public class EditPageController {

    @RequestMapping(method = RequestMethod.GET)
    public String printEditUser(@RequestParam("id") long id, ModelMap model) {
        User user = UserServiceImpl.getInstance().getUserById(id);
        model.addAttribute("user",user);
        model.addAttribute("id",id);
        return "edit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String editUser(@RequestParam("name") String name
            , @RequestParam("username") String username
            , @RequestParam("password") String password
            , @RequestParam("role") String role
            , @RequestParam("id") long id
            ) {
        User user = new User(name,username,password,role);
        try {
            UserServiceImpl.getInstance().updateUser(user,id);
            return "redirect:/main";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
