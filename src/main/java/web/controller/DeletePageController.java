package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.UserServiceImpl;

import java.sql.SQLException;

@Controller
@RequestMapping("/delete")
public class DeletePageController {


    @RequestMapping(method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") long id) {
        try {
            UserServiceImpl.getInstance().deleteUser(id);
            return "redirect:/main";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
