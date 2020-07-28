package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.interf.RoleService;
import web.service.interf.UserService;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping
    public String printAllUsers(ModelMap model) throws SQLException {

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "mainpage";
    }


    @GetMapping("addUser")
    public ModelAndView newUser() {
        User user = new User();
        ModelAndView mav = new ModelAndView("adduser");
        mav.addObject("user", user);

        List<Role> roles = roleService.getAllRoles();

        mav.addObject("allRoles", roles);

        return mav;
    }

    @PostMapping("addUser")
    public String addUser(@ModelAttribute("userForm") User user) {
//        System.out.println("+++++++++++++++" + user.getRoles());
        Set<Role> roles = new HashSet<>();
        if (user.getRoles().contains("1")) {
            roles.add(roleService.getRole("ADMIN"));
        }
        if (user.getRoles().contains("2")) {
            roles.add(roleService.getRole("USER"));
        }


        String pass = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(pass));

        try {
            if (Objects.nonNull(roles) && userService.addUser(user)) {
                user.setRoles(roles);
                userService.addUser(user);
            } else {
                return "error";
            }
            return "redirect:/admin";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("edit")
    public String printEditUser(@RequestParam("id") long id, ModelMap model) throws SQLException {
        User user = userService.getUsersById(id);

        model.addAttribute("user", user);
        model.addAttribute("id", id);

        List<Role> roles = roleService.getAllRoles();

        model.addAttribute("allRoles", roles);
        model.addAttribute("userRoles", user.getRoles());


        return "edit";
    }

    @PostMapping("edit")
    public String updateUser(@ModelAttribute("userEditForm") User user) {

//        System.out.println("++++A+A+A++A+A+A+  " + user.getRoles());

        Set<Role> roles1 = new HashSet<>();
        if (user.getRoles().contains("1")) {
            roles1.add(roleService.getRole("ADMIN"));
        }
        if (user.getRoles().contains("2")) {
            roles1.add(roleService.getRole("USER"));
        }

        user.setRoles(roles1);
        try {
            if (Objects.nonNull(roles1) && userService.addUser(user)) {
                String pass = user.getPassword();
                user.setPassword(bCryptPasswordEncoder.encode(pass));
                userService.updateUser(user);
            } else {
                return "error";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/admin";
    }

    @GetMapping("delete")
    public String deleteUser(@RequestParam("id") long id) throws SQLException {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


}
