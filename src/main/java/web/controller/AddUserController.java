//package web.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//import web.model.Role;
//import web.model.User;
//import web.service.interf.RoleService;
//import web.service.interf.UserService;
//
//import java.sql.SQLException;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Controller
//@RequestMapping("/addUser")
//public class AddUserController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private RoleService roleService;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//
//    @GetMapping
//    public ModelAndView newUser() {
//        User user = new User();
//        ModelAndView mav = new ModelAndView("adduser");
//        mav.addObject("user", user);
//
//        List<Role> roles = roleService.getAllRoles();
//
//        mav.addObject("allRoles", roles);
//
//        return mav;
//    }
//
//
//    @PostMapping
//    public String addUser(@ModelAttribute("userForm") User user) {
//
//        Set<Role> roles = new HashSet<>();
//
//
//
//        if (user.getRoles().contains("1")) {
//            roles.add(roleService.getRole("ADMIN"));
//        } if (user.getRoles().contains("2")) {
//            roles.add(roleService.getRole("USER"));
//        }
//
//        System.out.println(roles.toString());
//        String pass = user.getPassword();
//        user.setPassword(bCryptPasswordEncoder.encode(pass));
//
//        try {
//            user.setRoles(roles);
//            userService.addUser(user);
//
//
//            return "redirect:/login";
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//
//
//    }
//
//}
